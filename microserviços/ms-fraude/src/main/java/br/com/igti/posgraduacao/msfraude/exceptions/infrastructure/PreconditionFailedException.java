package br.com.igti.posgraduacao.msfraude.exceptions.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PreconditionFailedException extends RuntimeException {

        private static final long serialVersionUID = 1L;
        private String code;
        private String message;
        private String developerMessage;
        private String origin;

        @Override
        public String toString() {
            return "PreconditionFailedException{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", developerMessage='" + developerMessage + '\'' +
                    ", origin='" + origin + '\'' +
                    '}';
        }
}
