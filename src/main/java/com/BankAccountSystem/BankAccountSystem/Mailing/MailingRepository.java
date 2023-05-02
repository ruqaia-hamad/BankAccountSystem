package com.BankAccountSystem.BankAccountSystem.Mailing;

public interface MailingRepository  {
    String sendSimpleMailToMany(MailingModel mailingModel);
    String sendSimpleMail(MailingModel mailingModel);

    String sendMailWithAttachmentToMany(MailingModel mailingModel);
    String sendMailWithAttachment(MailingModel mailingModel);
}
