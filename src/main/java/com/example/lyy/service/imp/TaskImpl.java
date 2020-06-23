package com.example.lyy.service.imp;


import com.example.lyy.entity.TPremFactorRela;
import com.example.lyy.mapper.TPremFactorRelaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Component

public class TaskImpl {



    @Autowired
    TPremFactorRelaMapper tPremFactorRelaMapper;

    @Async("taskExecutor")
    public Future<String> insertDataBase(StringBuilder stringBuilder,String code, List<String> factorCodes, List<TPremFactorRela> tPremFactorRelas, List<TPremFactorRela> factorRelas){
        for (String factorCode : factorCodes) {
            if ("19YZ646902".equals(factorCode)){
                factorRelas.add(tPremFactorRelas.parallelStream().filter(prem -> "19YZ646902".equals(prem.getFactorCode())).findFirst().get());
            }
            if("19YZ1666608097243959296".equals(factorCode)){
                factorRelas.add(tPremFactorRelas.parallelStream().filter(prem -> "19YZ1666608097243959296".equals(prem.getFactorCode())).findFirst().get());
            }
            if ("19YZ1608472605336010752".equals(factorCode)){
                factorRelas.add(tPremFactorRelas.parallelStream().filter(prem -> "19YZ1608472605336010752".equals(prem.getFactorCode())).findFirst().get());
            }
            if ("19YZ1629583211409707008".equals(factorCode)){
                factorRelas.add(tPremFactorRelas.parallelStream().filter(prem -> "19YZ1629583211409707008".equals(prem.getFactorCode())).findFirst().get());
            }
            if ("19YZ1668993075403554816".equals(factorCode)){
                factorRelas.add(tPremFactorRelas.parallelStream().filter(prem -> "19YZ1668993075403554816".equals(prem.getFactorCode())).findFirst().get());
            }
            //投保高风险（旅意险）
            if ("19YZ1661874493527949312".equals(factorCode)){
                factorRelas.add(TPremFactorRela.builder()
                        .productCode(code)
                        .channelCode("tour")
                        .factorCode("19YZ1661874493527949312")
                        .fullpathElement("information.extendList.S_A_000022")
                        .build());
            }
            if ("19YZ1725973736504692659".equals(factorCode)){
                factorRelas.add(TPremFactorRela.builder()
                        .productCode(code)
                        .channelCode("tour")
                        .factorCode("19YZ1725973736504692659")
                        .fullpathElement("insureds.infos.extendList.S_I_000262")
                        .build());
            }
            //绿色救援联盟”调整因子
            if ("19YZ1666411061324283904".equals(factorCode)){
                factorRelas.add(TPremFactorRela.builder()
                        .productCode(code)
                        .channelCode("tour")
                        .factorCode("19YZ1666411061324283904")
                        .fullpathElement("information.extendList.S_A_000023")
                        .build());
            }
            if("19YZ1666608097243959296".equals(factorCode)){
                factorRelas.add(TPremFactorRela.builder()
                        .productCode(code)
                        .channelCode("tour")
                        .factorCode("19YZ1666608097243959296")
                        .fullpathElement("information.term")
                        .build());
            }
        }
        if(!CollectionUtils.isEmpty(factorRelas)){
            return new AsyncResult<>("0000");
        }else{
            stringBuilder.append("," + code +"没有可用的因子");
            return new AsyncResult<>("9999");
        }
    }
}
