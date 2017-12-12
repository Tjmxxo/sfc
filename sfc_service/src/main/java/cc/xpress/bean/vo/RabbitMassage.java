package cc.xpress.bean.vo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 18:01
 * @modified By:
 */
public class RabbitMassage implements Serializable{
    private String messageType;
    private Integer messageId;
    private Long[] selectIds;
    private long messageCreateTime;

    public RabbitMassage() {
    }

    public Long[] getSelectIds() {
        return selectIds;
    }

    public void setSelectIds(Long[] selectIds) {
        this.selectIds = selectIds;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public long getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(long messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    @Override
    public String toString() {
        return "RabbitMassage{" +
                "messageType='" + messageType + '\'' +
                ", messageId=" + messageId +
                ", selectIds=" + Arrays.toString(selectIds) +
                ", messageCreateTime=" + messageCreateTime +
                '}';
    }
}
