package icu.junyao.serviceBase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JunYaoException extends RuntimeException {
    private Integer code;
    private String msg;
}
