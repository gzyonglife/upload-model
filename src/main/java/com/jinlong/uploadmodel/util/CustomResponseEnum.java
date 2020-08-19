package com.jinlong.uploadmodel.util;

/**
 * @description: CustomResponseEnum
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/4 15:16
 */
public enum CustomResponseEnum {
    //
    AUTHORITY_INSUFFICIENT(403, "权限不足"),

    LOGIN_OK(200, "用户登录成功"),

    CREATE_PROJECT_PLAN_OK(200, "添加项目计划实施信息成功"),
    PROJECT_PLAN_EXIST(304, "该项目已存在计划实施信息,新建任务被禁止"),
    CREATE_PROJECT_PLAN_FAILURE(500, "添加项目计划实施信息失败"),

    GET_PROJECT_PLAN_OK(200, "获取项目计划实施信息成功"),
    GET_PROJECT_PLAN_FAILURE(500, "获取项目计划实施信息失败"),

    CREATE_USER_OK(200, "创建新用户成功"),
    CREATE_USER_FAILURE(500, "创建新用户失败"),
    GET_USER_LIST_OK(200, "获取用户信息列表成功"),
    GET_USER_LIST_FAILURE(500, "获取用户信息列表失败"),
    GET_USER_OK(200, "获取用户信息成功"),
    GET_USER_FAILURE(500, "获取用户信息失败"),

    GET_ROLE_FAILURE(500, "获取权限列表失败"),
    GET_ROLE_OK(200, "获取权限列表成功"),

    GET_PROJECT_LIST_FAILURE(500, "获取项目列表失败"),
    GET_PROJECT_LIST_OK(200, "获取项目列表成功"),
    GET_PROJECT_FAILURE(500, "获取项目列表失败"),
    GET_PROJECT_OK(200, "获取项目列表成功"),
    CREATE_PROJECT_FAILURE(500, "创建项目失败"),
    CREATE_PROJECT_OK(200, "新建项目成功"),

    GET_PROJECT_MODEL_TYPE_OK(200, "获取项目类型列表成功"),
    GET_PROJECT_MODEL_TYPE_FAILURE(204, "未获取到项目类型列表"),

    GET_PROJECT_CATEGORY_LIST_FAILURE(500, "获取项目分类列表失败"),
    GET_PROJECT_PAGE_SEARCH_OK(200, "搜索并分页获取项目列表成功"),
    GET_PROJECT_CATEGORY_LIST_OK(200, "获取项目分类列表成功"),
    GET_PROJECT_CATEGORY_FAILURE(500, "获取项目分类列表失败"),
    GET_PROJECT_CATEGORY_OK(200, "获取项目分类列表成功"),
    CREATE_PROJECT_CATEGORY_FAILURE(500, "创建项目分类失败"),
    CREATE_PROJECT_CATEGORY_OK(200, "新建项目分类成功"),

    UPLOAD_PROJECT_MODEL_OK(200, "上传项目模型成功"),
    UPLOAD_PROJECT_MODEL_FAILURE(500, "上传项目模型失败"),
    GET_PROJECT_MODEL_OK(200, "获取项目模型成功"),
    GET_PROJECT_MODEL_NOT_EXIST(404, "获取项目模型为空"),
    GET_PROJECT_MODEL_FAILURE(500, "获取项目模型失败"),

    GET_STATISTICS_PROJECT_TYPE_OK(200,"获取项目类型统计"),
    GET_STATISTICS_PROJECT_TYPE_FAILURE(500,"获取项目类型统计失败"),

    GET_PROJECT_BY_ID_OK(200,"获取项目详情信息成功"),
    GET_PROJECT_BY_ID_FAILURE(200,"获取项目详情信息失败");





    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CustomResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}