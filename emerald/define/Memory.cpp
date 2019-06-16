//
// Created by 卢恕志 on 2019-06-16.
//

#include "Memory.h"
#include <map>


int Memory::registerScriptMatches() {
    matches->insert(pair<string,string>("var","[a-z0-9A-Z_]+=[\\s\\S]+"));
    matches->insert(pair<string,string>("list","(list)"));
    matches->insert(pair<string,string>("unset","unset [a-z0-9A-Z_]+(=[\\s\\S]+)*"));
    matches->insert(pair<string,string>("func","([a-z0-9A-Z_]=)*[\\s\\S]+\\(([\\s\\S]+|[\\s\\S]*)\\)(\\{([\\s\\S]+|)\\})*"));
    matches->insert(pair<string,string>("dfunc","func [\\s\\S]+\\(([\\s\\S]*|[\\s\\S]+)\\);start:[\\s\\S]+:end"));
    matches->insert(pair<string,string>("startfunc","func [\\s\\S]+\\(([\\s\\S]+|)\\);start:"));
    matches->insert(pair<string,string>("pc","let [a-zA-Z0-9_]+=[\\s\\S]+"));
    matches->insert(pair<string,string>("ffunc","([a-z0-9A-Z_]=)*[\\s\\S]+\\(([\\s\\S]+|[\\s\\S]*)\\)\\{([\\s\\S]+|)\\}"));
    matches->insert(pair<string,string>("start","([\\s\\S]+|)\\{"));
    return 0;
}
int Memory::registerCommand() {
    commands.push_front("func");
    commands.push_front("start");
    commands.push_front("end");
    commands.push_front("NULL");
    commands.push_front("unset");
    commands.push_front("list");
    commands.push_front("let");
    return 0;
}
