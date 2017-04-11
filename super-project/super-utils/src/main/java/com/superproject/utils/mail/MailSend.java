//package com.ujokey.utils.mail;
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Address;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class MailSend {	
//    private String host = "smtp.qq.com";
//    /**
//     * 发送邮件
//     * @param receiver 接收人
//     * @param title    标题
//     * @param content  内容
//     * @throws Exception
//     */
//    public void send(String receiver,String title,String content,String sendUser,String sendUserId,String sendPassword) throws Exception{
//        try
//        {         
//            Properties props = new Properties(); // 获取系统环境
//            Authenticator auth = new Authenticator() {
//            	
//            };//// 进行邮件服务器用户认证
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.auth", "true");
//            Session session = Session.getDefaultInstance(props, auth);
//            MimeMessage message = new MimeMessage(session);// 设置session,和邮件服务器进行通讯。
//            message.setSubject(title); // 设置邮件主题
//            message.setText(content); // 设置邮件正文
//            message.setSentDate(new Date()); // 设置邮件发送日期
//            Address address = new InternetAddress(sendUser);
//            message.setFrom(address); // 设置邮件发送者的地址
//            message.reply(false);
//            //处理多用户发送
//            Address[] multipleAddress = null;
//            String[] receivers = receiver.split(",");
//            if(receivers.length>0){
//            	multipleAddress = new Address[receivers.length];
//            	for(int i=0;i<receivers.length;i++){
//            	String tmpReceiver = receivers[i].trim();
//            	Address toAddress = new InternetAddress(tmpReceiver);
//            	multipleAddress[i] = address;
//            	}
//            }
//            message.addRecipients(Message.RecipientType.TO, multipleAddress); //收件人
//            Transport.send(message); // 发送邮件
//            
//        } catch (Exception ex){
//            ex.printStackTrace();
//            throw new Exception(ex.getMessage());
//        }
//    }