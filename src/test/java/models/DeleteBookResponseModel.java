package models;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class DeleteBookResponseModel {
    String  userId,
            isbn,
            message;
}