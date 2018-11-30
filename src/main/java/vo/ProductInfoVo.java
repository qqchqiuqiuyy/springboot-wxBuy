package vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuyu
 * @since 2017/11/21 14:35
 */
@Data
public class ProductInfoVo {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String description;
    @JsonProperty("icon")
    private String icon;
}
