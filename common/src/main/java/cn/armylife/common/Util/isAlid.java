package cn.armylife.common.Util;

import cn.armylife.common.Domain.Alid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class isAlid {

    private boolean isAlid(String code){
        Alid alid=new Alid();
        List<String> alids=alid.getAppId();
        for (int i=0;i<alids.size();i++){
            if (code.equals(alids.get(i))){
                return true;
            }
            return false;
        }
        return false;
    }
}
