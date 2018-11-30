package vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuyu
 * @since 2017/11/21 14:17
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -806829457890929391L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
}
