package vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yuyu
 * @since 2017/11/21 14:31
 */
@Data
public class ProductVo {
    /**
     * 名称
     */
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVos;
}
