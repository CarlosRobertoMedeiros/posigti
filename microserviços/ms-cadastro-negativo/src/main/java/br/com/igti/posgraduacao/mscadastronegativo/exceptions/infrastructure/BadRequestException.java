package br.com.igti.posgraduacao.mscadastronegativo.exceptions.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String developerMessage;
    private String origin;

    @Override
    public String toString() {
        return "BadRequestException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
