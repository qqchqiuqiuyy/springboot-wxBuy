package utils;

import vo.ResultVo;

/**
 * @author yuyu
 * @since 2017/11/21 15:38
 */
public class ResultVoUtils {

    /**
     * @param object
     * @return
     */
    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        return resultVo;
    }

    /**
     * 成功无返回值
     *
     * @return
     */
    public static ResultVo success() {
        return success(null);
    }

    /**
     * sss
     * 失败提示
     *
     * @param code
     * @param mesg
     * @return
     */
    public static ResultVo error(Integer code, String mesg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(mesg);
        return resultVo;
    }
}
