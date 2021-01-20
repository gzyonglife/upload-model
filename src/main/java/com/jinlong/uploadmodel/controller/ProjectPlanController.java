package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.*;
import com.jinlong.uploadmodel.service.ProjectPlanService;
import com.jinlong.uploadmodel.service.ProjectService;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @description: 项目计划实施
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 16:15
 */
@Slf4j
@RestController
@RequestMapping("projectPlan")
public class ProjectPlanController {

    @Autowired
    ProjectPlanService projectPlanService;

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    /**
     * 获取项目计划实施信息
     *
     * @param projectPlanId 计划实施id
     * @param userDetails   当前登录的用户
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getPlan/projectId")
    public ResponseEntity<?> getPlanToProjectId(
            @RequestParam @Validated @NotNull(message = "projectPlanId是必须的") Integer projectPlanId,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断该用户是否有该项目计划实施所属项目的权限
        if (!userService.hasProjectPlan(userDetails, projectPlanId)) {
            log.info("{}用户未拥有{}项目计划实施的权限，禁止获取计划实施信息", userDetails.getUsername(), projectPlanId);
            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
        }

        // 获取项目计划实施信息
        ProjectPlanVo  result = projectPlanService.getPlanForProjectId(projectPlanId);
        if (result == null) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_PLAN_FAILURE);
        } else {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.GET_PROJECT_PLAN_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECT_PLAN_OK.getMessage())
                    .data(result)
                    .build();
        }
    }

    /**
     * 新增项目计划实施信息
     *
     * @param projectPlanVo
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("add")
    public ResponseEntity<?> createPlan(
            @RequestBody @Validated ProjectPlanVo projectPlanVo,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断该用户是否有该项目计划实施所属项目的权限
//        if (!userService.hasProject(userDetails, projectPlanVo.getProjectId())) {
//            log.info("{}用户未拥有{}项目的权限，禁止为该项目新建计划实施信息", userDetails.getUsername(), projectPlanVo.getProjectId());
//            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
//        }
//        // 判断该项目计划实施所属项目是否以及含有本信息;
//        if (projectService.hasProjectPlan(projectPlanVo.getProjectId(), projectPlanVo.getPlanType())) {
//            log.info("{}项目已拥有计划实施信息，禁止为该项目新建计划实施信息", projectPlanVo.getProjectId());
//            return ResponseEntity.createFromEnum(CustomResponseEnum.PROJECT_PLAN_EXIST);
//        }
        // 进行添加
        Integer planId = projectPlanService.createPlan(projectPlanVo);
        if (planId <= 0) {
            log.info("{}项目添加计划实施信息失败", projectPlanVo.getProjectId());
            return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_PROJECT_PLAN_FAILURE);
        }
        log.info("{}项目添加计划实施信息成功，新增的计划实施信息id为：{}", projectPlanVo.getProjectId(), planId);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getMessage())
                .data(planId)
                .build();
    }


    /**
     * 修改项目计划实施信息
     *
     * @param projectPlanVo
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("update")
    public ResponseEntity<?> updatePlan(
            @RequestBody @Validated ProjectPlanVo projectPlanVo,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断该用户是否有该项目计划实施所属项目的权限
//        if (!userService.hasProject(userDetails, projectPlanVo.getProjectId())) {
//            log.info("{}用户未拥有{}项目的权限，禁止为该项目新建计划实施信息", userDetails.getUsername(), projectPlanVo.getProjectId());
//            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
//        }
//        // 判断该项目计划实施所属项目是否以及含有本信息;
//        if (projectService.hasProjectPlan(projectPlanVo.getProjectId(), projectPlanVo.getPlanType())) {
//            log.info("{}项目已拥有计划实施信息，禁止为该项目新建计划实施信息", projectPlanVo.getProjectId());
//            return ResponseEntity.createFromEnum(CustomResponseEnum.PROJECT_PLAN_EXIST);
//        }

        log.info("{}项目修改计划实施信息成功，修改的计划实施信息id为：{}", projectPlanVo.getProjectId());
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getMessage())
                .data(projectPlanService.upodateProjectPlan(projectPlanVo)>=1?true:false)
                .build();
    }

    /**
     * 获取项目计划实施信息
     *
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getPlan/project")
    public ResponseEntity<?> getPlan(@RequestParam(name = "current") @Validated @NotNull(message = "current不为空") Long current,
                                     @RequestParam(name = "size") @Validated @NotNull(message = "size不为空") Long size,
                                     @AuthenticationPrincipal UserDetails userDetails){
        PageVo<Object> pageVo = new PageVo<>();
        pageVo.setCurrent(current);
        pageVo.setSize(size);
        if(projectPlanService.getPlanForProject(pageVo)==null){
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_PLAN_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_PLAN_OK.getMessage())
                .data(projectPlanService.getPlanForProject(pageVo))
                .build();
    }

    @GetMapping("getProjectPlanByLike")
    public ResponseEntity<?> getProjectPlanByLike(@RequestParam(name = "current") @Validated @NotNull(message = "current不为空") Integer current,
                                                  @RequestParam(name = "size") @Validated @NotNull(message = "size不为空") Integer size,
                                                  @RequestParam(name = "projectName", required = false) String projectName,
                                                  @RequestParam(name = "projectPlanYear", required = false) String projectPlanYear,
                                                  @RequestParam(name = "planType", required = false) Integer planType,
                                                  @AuthenticationPrincipal UserDetails userDetails) throws ParseException {
//        Integer projectId = null;
//        if(projectName!=null&&!projectName.equals("")){
//            projectId = projectService.searchProjectForPage(null,projectName,null,1,1).getData().get(0).getProjectId();
//        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_PLAN_OK.getMessage())
                .data(projectPlanService.getProjectPlanByLike(current,size,projectName,projectPlanYear,planType))
                .build();
    }


    @PostMapping("/delPlanId")
    ResponseEntity<?> delModelId(@RequestParam(name = "idList") List<Integer> idList,
                                 @AuthenticationPrincipal UserDetails userDetails){
        if(!projectPlanService.delProjectPlanByList(idList)){
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.DEL_PROJECT_PLAN_ERROR.getCode())
                    .message(CustomResponseEnum.DEL_PROJECT_PLAN_ERROR.getMessage())
                    .data(false)
                    .build();
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.DEL_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.DEL_PROJECT_PLAN_OK.getMessage())
                .data(true)
                .build();
    }

    // excel导入
    @CrossOrigin
    @PostMapping(value = "/batch_sms_send/parseExcel/plan", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> parseExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = file.getInputStream();
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
            // 2.读取页脚sheet
            HSSFSheet sheetAt = wb.getSheetAt(0);
            // 3.循环读取某一行
            for (Row row : sheetAt) {
                ProjectPlanTable projectPlanTable = new ProjectPlanTable();
                // 直接添加所有信息
                for(int i = 0;i<sheetAt.getRow(0).getPhysicalNumberOfCells();i++){
                    row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                }
                // 4.读取每一行的单元格
                if(row.getCell(0)!=null) {
                    List<ProjectVo> list = projectService.searchProjectForPage(null,row.getCell(0).getStringCellValue(),"",1,1).getData();
                    if(list.size()!=0){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        projectPlanTable.setProjectId(list.get(0).getProjectId());
                        if(row.getCell(1).getStringCellValue()!=null){
                            projectPlanTable.setProjectPlanYear(sdf.parse(row.getCell(1).getStringCellValue()));
                        }
                        if(row.getCell(2).getStringCellValue()!=null){
                            projectPlanTable.setProjectPlanExpectStartTime(sdf.parse(row.getCell(2).getStringCellValue()));
                        }
                        if(row.getCell(3).getStringCellValue()!=null){
                            projectPlanTable.setProjectPlanExpectEndTime(sdf.parse(row.getCell(3).getStringCellValue()));
                        }
                        if(row.getCell(4).getStringCellValue()!=null){
                            projectPlanTable.setProjectPlanInvestTotal(Double.parseDouble(row.getCell(4).getStringCellValue()));
                        }
                        if(row.getCell(5).getStringCellValue()!=null){
                            projectPlanTable.setProjectPlanInvestFinish(Double.parseDouble(row.getCell(5).getStringCellValue()));
                        }
                        projectPlanTable.setProjectPlanJanuary(row.getCell(6).getStringCellValue());
                        projectPlanTable.setProjectPlanFebruary(row.getCell(7).getStringCellValue());
                        projectPlanTable.setProjectPlanMarch(row.getCell(8).getStringCellValue());
                        projectPlanTable.setProjectPlanApril(row.getCell(9).getStringCellValue());
                        projectPlanTable.setProjectPlanMay(row.getCell(10).getStringCellValue());
                        projectPlanTable.setProjectPlanJune(row.getCell(11).getStringCellValue());
                        projectPlanTable.setProjectPlanJuly(row.getCell(12).getStringCellValue());
                        projectPlanTable.setProjectPlanAugust(row.getCell(13).getStringCellValue());
                        projectPlanTable.setProjectPlanSeptember(row.getCell(14).getStringCellValue());
                        projectPlanTable.setProjectPlanOctober(row.getCell(15).getStringCellValue());
                        projectPlanTable.setProjectPlanNovember(row.getCell(16).getStringCellValue());
                        projectPlanTable.setProjectPlanDecember(row.getCell(17).getStringCellValue());
                        if(row.getCell(18).getStringCellValue()!=null){
                            projectPlanTable.setPlanType(row.getCell(18).getStringCellValue()=="1"?true:false);
                        }
                        if(row.getCell(19).getStringCellValue()!=null){
                            projectPlanTable.setIsFocus(Integer.parseInt(row.getCell(19).getStringCellValue()));
                        }
                        projectPlanTable.setItemNumber(row.getCell(20).getStringCellValue());
                        projectPlanTable.setProjectNote(row.getCell(21).getStringCellValue());
                        projectPlanService.insertProjectPlan(projectPlanTable);
                    }

                }


            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getMessage())
                .data(true)
                .build();
    }

}
