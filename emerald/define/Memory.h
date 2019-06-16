//
// Created by mgl on 2019-06-16.
//

#ifndef EMERALD_MEMORY_H
#define EMERALD_MEMORY_H

#include <map>
#include <list>

using namespace std;

class Memory {
    public:
        static map<string,string>* matches;
        static list<string> commands;
        static int registerScriptMatches();
        static int registerCommand();
        int registerScript = registerScriptMatches();
        int registerMatches = registerCommand();
};


#endif //EMERALD_MEMORY_H
