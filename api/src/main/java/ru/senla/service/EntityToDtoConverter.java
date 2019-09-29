package ru.senla.service;

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

import java.util.List;

public interface EntityToDtoConverter {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    AdTypeDto adTypeToAdTypeDto(AdType adType);
    AdType adTypeDtoToAdType(AdTypeDto adTypeDto);
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);
    AdDto adToAdDto(Ad ad);
    Ad adDtoToAd(AdDto adDto);
    List<CommentDto> commentListToCommentDtoList(List<Comment> commentList);
    List<Comment> commentListDtoToComment(List<CommentDto> commentDtoList);
    List<AdDto> adListToAdDtoList(List<Ad> adList);
    Credential credentialDtoToCredential(CredentialDto credentialDto);
    CredentialDto credentialToCredentialDto(Credential credential);
    List<CredentialDto> credentialListToCredentialDtoList(List<Credential> credentialList);
    List<Credential> credentialDtoListToCredentialList(List<CredentialDto> credentialDtoList);
    public List<AdType> adTypeDtoListToAdTypeList(List<AdTypeDto> adTypeDtoList);
    List<AdTypeDto> adTypeListToAdTypeDtoList(List<AdType> adTypeList);
    public List<User> userDtoListToUserList(List<UserDto> userDtoList);
    List<UserDto> userListToUserDtoList(List<User> userList);
    Message messageDtoToMessage(MessageDto messageDto);
    MessageDto messageToMessageDto(Message message);
    Chat chatDtoToChat(ChatDto chatDto);
    ChatDto chatToChatDto(Chat chat);
    List<ChatDto> chatListToChatDtoList(List<Chat> chatList);
    List<Chat> chatDtoListToChatList(List<ChatDto> chatDtoList);
    List<Message> messageDtoListToMessageList(List<MessageDto> messageDtoList);
    List<MessageDto> messageListToMessageDtoList(List<Message> messageList);
}
