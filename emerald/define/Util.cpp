//
// Created by mgl on 2019-06-16.
//
#include "regex"
#include "string"
using namespace std;

inline bool match(string&,string);

inline vector<string> split(string,char);

inline bool match(string& target,string reg){
    smatch s;
    return regex_match(target,s,regex("^"+reg+"$"));
}
inline vector<string> split(string s, char c)
{
    string buff{""};
    vector<string> v;

    for(auto n:s)
    {
        if(n != c) buff+=n;
        else if(n == c && buff != "") { v.push_back(buff); buff = ""; }
    }
    if(buff != "") v.push_back(buff);

    return v;
}
template <class T> inline void lostHead(vector<T>* vector){

}


