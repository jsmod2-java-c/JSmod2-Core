//
// Created by 卢恕志 on 2019-06-16.
//

#ifndef EMERALD_VAR_H
#define EMERALD_VAR_H

#include "Memory.h"
using namespace std;
class Var : public Memory{
    private:
        string value;
        string type;
        bool isNull;
        string name;
        //构造方法
        Var(string,string);
        string parseType(string);
    public:
        const string &getValue() const;

        void setValue(const string &value);

        const string &getType() const;

        bool isNull1() const;

        const string &getName() const;

        void unset();

        static Var* compile(string command);
};


#endif //EMERALD_VAR_H
