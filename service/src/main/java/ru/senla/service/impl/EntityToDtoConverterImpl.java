package ru.senla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.senla.dto.AdDto;
import ru.senla.dto.AdTypeDto;
import ru.senla.dto.ChatDto;
import ru.senla.dto.CommentDto;
import ru.senla.dto.CredentialDto;
import ru.senla.dto.MessageDto;
import ru.senla.dto.UserDto;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Chat;
import ru.senla.entity.Comment;
import ru.senla.entity.Credential;
import ru.senla.entity.Message;
import ru.senla.entity.User;
import ru.senla.service.EntityToDtoConverter;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityToDtoConverterImpl implements EntityToDtoConverter {

    @Autowired
    private PasswordEncoder encoder;

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.isGender());
        userDto.setUserRating(user.getUserRating());
        if (user.getCredential() != null) {
            userDto.setCredentialId(user.getCredential().getCredentialId());
        }
        return userDto;
    }

    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setBirthDate(userDto.getBirthDate());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGender(userDto.isGender());
        user.setUserRating(userDto.getUserRating());
        return user;
    }

    public AdTypeDto adTypeToAdTypeDto(AdType adType) {
        AdTypeDto adTypeDto = new AdTypeDto();
        adTypeDto.setId(adType.getId());
        adTypeDto.setCategory(adType.getCategory());
        adTypeDto.setClassification(adType.getClassification());
        adTypeDto.setBuyOrSale(adType.getBuyOrSale());
        return adTypeDto;
    }

    public AdType adTypeDtoToAdType(AdTypeDto adTypeDto) {
        if (adTypeDto == null) {
            return null;
        }
        AdType adType = new AdType();
        adType.setId(adTypeDto.getId());
        adType.setCategory(adTypeDto.getCategory());
        adType.setClassification(adTypeDto.getClassification());
        adType.setBuyOrSale(adTypeDto.isBuyOrSale());
        return adType;
    }

    public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        User user = comment.getUser();
        commentDto.setUserId(user.getId());
        commentDto.setCommentDate(comment.getCommentDate());
        commentDto.setCommentMessage(comment.getCommentMessage());
        if (comment.getAd() != null) {
            commentDto.setAdId(comment.getAd().getId());
        }
        return commentDto;
    }

    public Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setCommentDate(commentDto.getCommentDate());
        comment.setCommentMessage(commentDto.getCommentMessage());
        return comment;
    }

    public AdDto adToAdDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setId(ad.getId());
        UserDto userDto = userToUserDto(ad.getUser());
        adDto.setUserDto(userDto);
        AdTypeDto adTypeDto = adTypeToAdTypeDto(ad.getAdType());
        adDto.setAdTypeDto(adTypeDto);
        adDto.setAdMessage(ad.getAdMessage());
        adDto.setAdDate(ad.getAdDate());
        adDto.setAdTopDate(ad.getAdTopDate());
        List<CommentDto> commentDtoList = commentListToCommentDtoList(ad.getCommentsList());
        adDto.setCommentDtoList(commentDtoList);
        return adDto;
    }

    public Ad adDtoToAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setId(adDto.getId());
        User user = userDtoToUser(adDto.getUserDto());
        ad.setUser(user);
        AdType adType = adTypeDtoToAdType(adDto.getAdTypeDto());
        ad.setAdType(adType);
        ad.setAdMessage(adDto.getAdMessage());
        ad.setAdDate(adDto.getAdDate());
        ad.setAdTopDate(adDto.getAdTopDate());
        List<Comment> commentList = commentListDtoToComment(adDto.getCommentDtoList());
        ad.setCommentsList(commentList);
        return ad;
    }

    public List<CommentDto> commentListToCommentDtoList(List<Comment> commentList) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = commentToCommentDto(comment);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    public List<Comment> commentListDtoToComment(List<CommentDto> commentDtoList) {
        if (commentDtoList == null) {
            return null;
        }
        List<Comment> commentList = new ArrayList<>();
        for (CommentDto commentDto : commentDtoList) {
            Comment comment = commentDtoToComment(commentDto);
            commentList.add(comment);
        }
        return commentList;
    }

    public List<AdDto> adListToAdDtoList(List<Ad> adList) {
        List<AdDto> adDtoList = new ArrayList<>();
        for (Ad ad : adList) {
            AdDto adDto = adToAdDto(ad);
            adDtoList.add(adDto);
        }
        return adDtoList;
    }

    public List<Credential> credentialDtoListToCredentialList(List<CredentialDto> credentialDtoList) {
        List<Credential> credentialList = new ArrayList<>();
        for (CredentialDto credentialDto : credentialDtoList) {
            Credential credential = credentialDtoToCredential(credentialDto);
            credentialList.add(credential);
        }
        return credentialList;
    }

    public List<CredentialDto> credentialListToCredentialDtoList(List<Credential> credentialList) {
        List<CredentialDto> credentialDtoList = new ArrayList<>();
        for (Credential credential : credentialList) {
            CredentialDto credentialDto = credentialToCredentialDto(credential);
            credentialDtoList.add(credentialDto);
        }
        return credentialDtoList;
    }

    public List<AdTypeDto> adTypeListToAdTypeDtoList(List<AdType> adTypeList) {
        List<AdTypeDto> adTypeDtoList = new ArrayList<>();
        for (AdType adType : adTypeList) {
            AdTypeDto adTypeDto = adTypeToAdTypeDto(adType);
            adTypeDtoList.add(adTypeDto);
        }
        return adTypeDtoList;
    }

    public List<AdType> adTypeDtoListToAdTypeList(List<AdTypeDto> adTypeDtoList) {
        List<AdType> adTypeList = new ArrayList<>();
        for (AdTypeDto adTypeDto : adTypeDtoList) {
            AdType adType = adTypeDtoToAdType(adTypeDto);
            adTypeList.add(adType);
        }
        return adTypeList;
    }

    public List<Message> messageDtoListToMessageList(List<MessageDto> messageDtoList) {
        List<Message> messageList = new ArrayList<>();
        for (MessageDto messageDto : messageDtoList) {
            Message message = messageDtoToMessage(messageDto);
            messageList.add(message);
        }
        return messageList;
    }

    public List<MessageDto> messageListToMessageDtoList(List<Message> messageList) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message : messageList) {
            MessageDto messageDto = messageToMessageDto(message);
            messageDtoList.add(messageDto);
        }
        return messageDtoList;
    }

    public List<User> userDtoListToUserList(List<UserDto> userDtoList) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = userDtoToUser(userDto);
            userList.add(user);
        }
        return userList;
    }

    public List<UserDto> userListToUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = userToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public List<Chat> chatDtoListToChatList(List<ChatDto> chatDtoList) {
        List<Chat> chatList = new ArrayList<>();
        for (ChatDto chatDto : chatDtoList) {
            Chat chat = chatDtoToChat(chatDto);
            chatList.add(chat);
        }
        return chatList;
    }

    public List<ChatDto> chatListToChatDtoList(List<Chat> chatList) {
        List<ChatDto> chatDtoList = new ArrayList<>();
        for (Chat chat : chatList) {
            ChatDto chatDto = chatToChatDto(chat);
            chatDtoList.add(chatDto);
        }
        return chatDtoList;
    }


    public CredentialDto credentialToCredentialDto(Credential credential) {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setCredentialId(credential.getCredentialId());
        credentialDto.setLogin(credential.getLogin());
        credentialDto.setPassword(credential.getPassword());
        credentialDto.setEmail(credential.getEmail());
        if (credential.getUser() != null) {
            credentialDto.setUserId(credential.getUser().getId());
        }
        credentialDto.setRole(credential.getRole());
        return credentialDto;
    }

    public Credential credentialDtoToCredential(CredentialDto credentialDto) {
        Credential credential = new Credential();
        credential.setCredentialId(credentialDto.getCredentialId());
        credential.setLogin(credentialDto.getLogin());
        String enc = encoder.encode(credentialDto.getPassword());
        credential.setPassword(enc);
        credential.setEmail(credentialDto.getEmail());
        credential.setRole(credentialDto.getRole());
        return credential;
    }

    public Message messageDtoToMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setText(messageDto.getText());
        message.setMessageDate(messageDto.getMessageDate());
        message.setUser(userDtoToUser(messageDto.getUserDto()));
        return message;
    }

    public MessageDto messageToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setText(message.getText());
        messageDto.setMessageDate(message.getMessageDate());
        messageDto.setUserDto(userToUserDto(message.getUser()));
        messageDto.setChatId(message.getChat().getId());
        return messageDto;
    }

    public Chat chatDtoToChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setId(chatDto.getId());
        chat.setChatName(chatDto.getChatName());
        if (chatDto.getUserDtoList() != null) {
            chat.setUserList(userDtoListToUserList(chatDto.getUserDtoList()));
        }

        return chat;
    }

    public ChatDto chatToChatDto(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        chatDto.setChatName(chat.getChatName());
        chatDto.setUserDtoList(userListToUserDtoList(chat.getUserList()));
        return chatDto;
    }

}
