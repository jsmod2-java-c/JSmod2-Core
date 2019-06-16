//
// Created by 卢恕志 on 2019-06-16.
//

#include <string>
#include "map"

#ifndef EMERALD_PARSER_H
#define EMERALD_PARSER_H

#define MAP map<string,Var*>

#endif //EMERALD_PARSER_H

using namespace std;

class EmeraldScript{

    private:
        MAP vars;
    public:

        static void* parse(string);

        string& parseVar(string&,MAP);

        string& parseVar(string&);
};
