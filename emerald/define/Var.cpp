//
// Created by magicLu550 on 2019-06-16.
//
#include "Var.h"
#include "String.h"
#include <regex>
#include "Util.cpp"

Var::Var(string name, string value):name(name),value(value) {
    this->type = this->parseType(value);
    if(value == "NULL"){
        this->isNull = "NULL";
    }

}
string Var::parseType(string val) {
    if(match(val,"[0-9]+")){
        return "INT";
    }else if(match(val,"[0-9]+\\.[0-9]+")){
        return "DOUBLE";
    }else if(match(val,"(true|false)")){
        return "BOOL";
    }else if(match(val,"NULL")){
        return "NULL";
    }else{
        return "STRING";
    }
}

const string &Var::getValue() const {
    return value;
}

void Var::setValue(const string &value) {
    string type = parseType(value);
    if(getType()=="NULL"){
        this->value = value;
        this->type = type;
        return;
    }
    if(type == getType()){
        this->value = value;
    }else{
        cout << "the type is error,the type is " << getType() << ",but the new type is" << type << endl;
        return;
    }
    Var::value = value;
}

const string &Var::getType() const {
    return type;
}


bool Var::isNull1() const {
    return isNull;
}

const string &Var::getName() const {
    return name;
}

void Var::unset() {
    this->type = "NULL";
    this->value= "NULL";
}
Var* Var::compile(string command) {

}


