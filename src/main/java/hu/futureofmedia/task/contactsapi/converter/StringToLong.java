package hu.futureofmedia.task.contactsapi.converter;

public class StringToLong {

    public static Long convert(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception ex) {
            return null;
        }
    }
}
