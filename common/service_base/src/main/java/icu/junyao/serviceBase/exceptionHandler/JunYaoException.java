package icu.junyao.serviceBase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JunYaoException extends RuntimeException {
    private Integer code;
    private String msg;
}
