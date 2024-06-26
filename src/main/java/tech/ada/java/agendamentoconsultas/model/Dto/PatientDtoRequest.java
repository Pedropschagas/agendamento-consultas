package tech.ada.java.agendamentoconsultas.model.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ada.java.agendamentoconsultas.exception.CpfNotValidException;
import tech.ada.java.agendamentoconsultas.utils.DocumentUtils;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class PatientDtoRequest implements Serializable{
    @Pattern(regexp = "^[\\p{L}]+$", message = "O nome deve conter apenas caracteres alfabéticos.")
    private String nome;
    @Email(message = "Coloque um email em um formato válido(ex: usuario@dominio.com")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_*#@]).{8,32}$",
    message = "A senha deve conter de 8 a 20 caracteres (lowercase, uppercase, numbers, special(_,*,#,@))")
    private String senha;
    @Pattern(regexp = "^\\(?(\\d{2})\\)?\\s?(\\d{4,5})-?(\\d{4})$", message = "O telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;
    private String cpf;
    private AddressRequestDto addressRequestDto;

    public PatientDtoRequest(String nome, String email, String senha, String telefone, String cpf, AddressRequestDto addressRequestDto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.setCpf(cpf);
        this.addressRequestDto = addressRequestDto;
    }

    public void setCpf(String cpf) {
        if(!DocumentUtils.cpfIsValid(cpf)){
            throw new CpfNotValidException();
        }
        this.cpf = cpf;
    }
}
