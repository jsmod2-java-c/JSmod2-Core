//
// Created by 卢恕志 on 2019-06-16.
//
#include "regex"
inline bool match(string target,string reg){
    smatch s;
    return regex_match(target,s,regex("^"+reg+"$"));
}

