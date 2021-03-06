package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.*;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.entity.vo.ProjectsVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.*;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @description: ProjectController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 11:48
 */
@Slf4j
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectCategoryService projectCategoryService;

    @Autowired
    ProjectPlanService projectPlanService;

    @Autowired
    ProjectDetailsTableService projectDetailsTableService;

    @Autowired
    FirmTableService firmTableService;

    @Autowired
    ProjectClassTableService projectClassTableService;

    @Autowired
    ProjectApprovalService projectApprovalService;

    @Autowired
    ProjectBeCompletedService projectBeCompletedService;

    @Autowired
    ProjectPublicityService projectPublicityService;

    /**
     * 获取项目列表
     *
     * @param current
     * @param size
     * @param userDetails
     * @return
     */
    @GetMapping("/getProject/all")
    public ResponseEntity<?> getProjectListOfPage(
            Integer type,
            @RequestParam(name = "current") @Validated @NotNull(message = "current不为空") Long current,
            @RequestParam(name = "size") @Validated @NotNull(message = "size不为空") Long size,
            @AuthenticationPrincipal UserDetails userDetails) {
        PageVo<Object> pageVo = new PageVo<>();
        pageVo.setCurrent(current);
        pageVo.setSize(size);
        PageVo<ProjectVo> result;
        result = projectService.getProjectListOfPage(pageVo,type);
        if (result == null || result.getData().isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_LIST_FAILURE);
        }
        List<ProjectVo> data = result.getData();
        for (int i = 0; i < data.size(); i++) {
            ProjectVo projectvo = data.get(i);
            projectvo.setProjectClassTableName(
                    projectCategoryService.getProjectCategoryById(projectvo.getProjectCategoryId()).getProjectCategoryName());
            data.set(i, projectvo);
        }
        List<ProjectVo> list = new ArrayList<ProjectVo>();
        for(ProjectVo vo : result.getData()){
            vo.setProjectClassTableName(projectCategoryService.getProjectCategoryById(vo.getProjectCategoryId()).getProjectCategoryName());
            if(vo.getProjectParent()!=null){
                if(projectService.getProjectById(vo.getProjectParent())!=null){
                    vo.setProjectParentName(projectService.getProjectById(vo.getProjectParent()).get().getProjectName());
                }
            }
            list.add(vo);
        }
        result.setData(list);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_LIST_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 新建项目
     *
     * @param projectVo
     * @param userDetails
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<?> addProject(@RequestBody @Validated ProjectVo projectVo, @AuthenticationPrincipal UserDetails userDetails) {
        ProjectVo result;
        result = projectService.addProject(projectVo);
        if (result == null) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_PROJECT_FAILURE);
        }else{
            PageVo<ProjectVo> page = projectService.searchProjectForPage(null,result.getProjectName(),null,1,1);
            if(!page.getData().isEmpty()){
                int projectId = page.getData().get(0).getProjectId();
                if(!projectApprovalService.addProjectApproval(ProjectApproval.builder().projectId(projectId).build())){
                    log.info("添加项目审批信息失败");
                }
                if(!projectBeCompletedService.addProjectBeCompleted(ProjectBeCompleted.builder().projectId(projectId).build())){
                    log.info("添加项目竣工信息失败");
                }
                if(!projectPublicityService.addProjectPublicity(ProjectPublicity.builder().projectId(projectId).build())){
                    log.info("添加项目公示信息失败");
                }
            }

        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 根据项目id获取项目信息
     *
     * @param id
     * @param userDetails
     * @return
     */
    @GetMapping("/getProject/id")
    public ResponseEntity<?> getProjectById(@RequestParam @Validated @NotNull(message = "项目id不得为空") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        ProjectsVo projectsVo = new ProjectsVo();

        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_BY_ID_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_BY_ID_OK.getMessage())
                .data(projectsVo)
                .build();
    }
    /**
     * 根据分类id获取项目
     *
     * @param categoryId
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/getProject/categoryId")
    public ResponseEntity<?> getProjectByCategoryId(@RequestParam @Validated @NotNull(message = "分类id不得为空") Integer categoryId, @AuthenticationPrincipal UserDetails userDetails) {
        List<ProjectVo> result;
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            // 允许查询
            result = projectService.getProjectByCategoryId(categoryId);
        } else {
            // 查询属于自己的项目
            result = projectService.getProjectByCategoryId(categoryId, userDetails.getId());
        }
        if (result.isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 根据分类id，名称，年份模糊查询项目信息
     *
     * @param projectCategoryId
     * @param name
     * @param year
     * @param current
     * @param size
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/search/project")
    public ResponseEntity<?> searchProjectForPage(@RequestParam(name = "projectCategoryId", required = false) Integer projectCategoryId,
                                                  @RequestParam(name = "name", required = false) String name,
                                                  @RequestParam(name = "year", required = false) String year,
                                                  @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                                                  @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(projectCategoryId);
        PageVo<ProjectVo> projectVoPageVo = projectService.searchProjectForPage(projectCategoryId, name, year, current, size);
        Assert.assertNotNull(projectVoPageVo, CustomExceptionEnum.GET_NONE);
        Assert.assertCollectionNotEmpty(projectVoPageVo.getData(), CustomExceptionEnum.GET_NONE);
        List<ProjectVo> list = new ArrayList<ProjectVo>();
        for(ProjectVo vo : projectVoPageVo.getData()){
            if(vo.getProjectCategoryId()==0 || vo.getProjectCategoryId()==null){
                vo.setProjectClassTableName("分类已删除");
            }else{
                if(projectCategoryService.getProjectCategoryById(vo.getProjectCategoryId())!=null){
                    vo.setProjectClassTableName(projectCategoryService.getProjectCategoryById(vo.getProjectCategoryId()).getProjectCategoryName());
                }else{
                    vo.setProjectClassTableName("分类已删除");
                }
            }
            if(vo.getProjectParent()!=null&&vo.getProjectParent()!=0){
                if(projectService.getProjectById(vo.getProjectParent())!=null){
                    vo.setProjectParentName(projectService.getProjectById(vo.getProjectParent()).get().getProjectName());
                }else{
                    vo.setProjectParentName(" ");
                }
            }else {
                vo.setProjectParentName(" ");
            }
            list.add(vo);
        }
        projectVoPageVo.setData(list);
        return ResponseEntity
                .builder()
                .data(projectVoPageVo)
                .code(CustomResponseEnum.GET_PROJECT_PAGE_SEARCH_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_PAGE_SEARCH_OK.getMessage())
                .build();
    }

    /**
     * 批量删除项目
     *
     * @param idList
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN')")
    @PostMapping("/del/project")
    public ResponseEntity<?> delPeojectAll(@RequestParam(name = "idList", required = false) List<Integer> idList,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        if (idList == null || idList.size() <= 0) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECT_LIST_FAILURE);
        }
        if (!projectService.delPeojectAll(idList)) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECT_LIST_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.DEL_PROJECT_LIST_OK.getCode())
                .message(CustomResponseEnum.DEL_PROJECT_LIST_OK.getMessage())
                .build();
    }


    /**
     * 修改项目信息
     * @param projectVo
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("update")
    public ResponseEntity<?> updateProject(@RequestBody @Validated ProjectVo projectVo, @AuthenticationPrincipal UserDetails userDetails) {
        ProjectVo entity = projectService.updateProject(projectVo);
        if (entity == null) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECT_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.UPDATE_PROJECT_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECT_OK.getMessage())
                .data(entity)
                .build();
    }


    // excel导入
    @CrossOrigin
    @PostMapping(value = "/batch_sms_send/parseExcel", produces = {"application/json;charset=UTF-8"})
    public void parseExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inputStream = file.getInputStream();
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
            // 2.读取页脚sheet
            HSSFSheet sheetAt = wb.getSheetAt(1);
            // 3.循环读取某一行
            for (Row row : sheetAt) {
                ProjectVo projectVo = new ProjectVo();
                projectVo.setUserId(1);
                // 直接添加所有信息
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                // 4.读取每一行的单元格
                if(row.getCell(0)!=null && row.getCell(1)!=null)
                    projectVo.setProjectName(row.getCell(0).getStringCellValue());
                    projectVo.setProjectCategoryId(projectCategoryService.getProjectCategoryByadd(row.getCell(1).getStringCellValue()));
                    projectVo.setProjectParent(
                        projectService.searchProjectForPage(0,row.getCell(3).getStringCellValue(),"",1,1).getData().get(0).getProjectId()
                    );
                projectService.addProject(projectVo);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @PostMapping("/addUrlImgVideo")
    public ResponseEntity<?> addImgVideo(MultipartFile[] imgFolder,
                                         MultipartFile[] videoFolder,
                                         @RequestParam("projectId") Integer projectId,
                                         @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.UPDATE_PROJECT_IMG_VIDEO_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECT_IMG_VIDEO_OK.getMessage())
                .data(projectService.addImgVideo(imgFolder,videoFolder,projectId))
                .build();
    }

    @GetMapping("/getProjectByAdministrative")
    public ResponseEntity<?> getProjectByAdministrative(@RequestParam("administrativeName")String administrativeName){
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_LIST_OK.getMessage())
                .data(projectService.getProjectByAdministrative(administrativeName))
                .build();
    }
}
