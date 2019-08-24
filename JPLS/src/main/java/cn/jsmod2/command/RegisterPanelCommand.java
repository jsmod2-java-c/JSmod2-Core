package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.web.utils.PasswordUtil;

public class RegisterPanelCommand extends NativeCommand {

    public RegisterPanelCommand() {
        super("register", Powers.CONSOLE, "register the Control Panel");
    }

    //register name pwd -m 修改密码
    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if (strings.length>=3&&strings[2].equals("-m")){
            PasswordUtil.modifyPassword(strings[0],strings[1]);
            return true;
        }
        PasswordUtil.writePassword(strings[0],strings[1]);
        return true;
    }
}
