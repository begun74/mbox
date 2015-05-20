package bvv.util.mailLog;

import java.io.File;
import java.util.List;

import bvv.util.mailLog.view.PostfixImpl;

interface StrategyImpl {
    
    void execute() throws Exception; 
    List<PostfixImpl> getData();
    void setData(List<PostfixImpl> PosI);
    
}