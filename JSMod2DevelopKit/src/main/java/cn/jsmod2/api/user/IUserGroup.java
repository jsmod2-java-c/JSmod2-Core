package cn.jsmod2.api.user;

public interface IUserGroup {

    Object getComponent();

    String getColor();

    String getBadgeText();

    long getPermissions();

    boolean isCover();

    boolean isHiddenByDefault();

    String getName();
}
