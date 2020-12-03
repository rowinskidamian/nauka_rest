package pl.damianrowinski.nauka_rest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {

    private Long id;

    @NotBlank
    @Size(max = 30)
    private String tweetTitle;

    @NotBlank
    private String tweetText;

    @NotNull
    private UserDTO userDTO;
}
