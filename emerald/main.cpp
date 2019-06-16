#include <iostream>
#include "Var.h"
#include "Var.cpp"
#include "Parser.h"
#include "Memory.cpp"
#include "Parser.cpp"
/**
 * emerald -c [code]
 * emerald -i [file]
 * @param argc
 * @param args
 * @return
 */
int main(int argc,char* args[]) {
    if(argc!=0){
        if(strcmp(args[0],"-c")==0){
            for(int i = 0;i<argc-1;i++){
                EmeraldScript::parse(args[i]);
            }
        }else if(strcmp(args[0],"-i")==0){

        }else{

        }
    }
    return 0;
}