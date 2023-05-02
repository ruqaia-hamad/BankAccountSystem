package com.BankAccountSystem.BankAccountSystem.Mailing;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailingModel {

    private List<String> recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
