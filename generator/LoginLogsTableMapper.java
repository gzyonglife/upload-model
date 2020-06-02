package generator;

import com.jinlong.uploadmodel.entity.data.LoginLogsTable;

public interface LoginLogsTableMapper {
    int deleteByPrimaryKey(Long logsId);

    int insert(LoginLogsTable record);

    int insertSelective(LoginLogsTable record);

    LoginLogsTable selectByPrimaryKey(Long logsId);

    int updateByPrimaryKeySelective(LoginLogsTable record);

    int updateByPrimaryKey(LoginLogsTable record);
}