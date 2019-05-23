package javac;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sangto.component.utils.ResponseInfo;
import com.sangto.exception.BusinessException;
import com.sangto.mode.bean.CommBean;
import com.sangto.mode.bus.Eapp_SaleBillD;
import com.sangto.mode.bus.Eapp_SaleBillH;
import com.sangto.mode.bus.Eapp_TradeBill;
import com.sangto.mode.bus.Eapp_TradeHead;
import com.sangto.mode.bus.Eapp_TradePay;
import com.sangto.mode.bus.RequestHead;
import com.sangto.service.EappCardService;
import com.sangto.service.EappService;
@Api("pos压力测试接口")
@Controller
@RequestMapping("LayerArchitecture/winpos/")
public class TestController {

    @Resource
    private EappService eappservice;

    @Resource
    private EappCardService cardcentservice;

    @Resource
    private EappController eappcontro;


    private static Log logger = LogFactory.getLog(TestController.class);

    @SuppressWarnings("unchecked")
    @ApiOperation("pos-中间件统压力测试")
    @ResponseBody
    @RequestMapping(value = "test_term",method ={RequestMethod.POST, RequestMethod.GET})
    public ResponseInfo<?> testinfo(@RequestParam final String reqjson){
        String Salebillno = "1000000119052200000";
        for (int i = 0; i < 10; i++) {
            long sale=Long.parseLong(Salebillno);
            sale = sale + 1;
            Salebillno =String.valueOf(sale);
            System.out.println("Salebillno"+Salebillno);
            CommBean commBean = JSON.parseObject(reqjson, CommBean.class);
            ResponseInfo<?> info= eappcontro.eapp_0330(commBean);
            Map<String, Object> m= (Map<String, Object>) info.getData();
            Object list= m.get("dataList");
            if(null == list){
                throw new BusinessException(-100,"不存在");
            }
            System.out.println("AAAAAAAAAAAAAA");
            CommBean commBean01= new CommBean();
            RequestHead req =new RequestHead();
            req.setFuncid("0311");
            req.setOprtno("999");
            req.setTermid("10000001");
            req.setReqdate(new Timestamp(new Date().getTime()));
            commBean01.setReq(req);
            commBean01.setSlcodetype("00");
            commBean01.setGdidentno("325011");
            ResponseInfo<?> info02=eappcontro.eapp_0311(commBean01);
            Map<String, Object> m1= (Map<String, Object>) info02.getData();
            logger.info(m1);
            Object list1= m1.get("dataList");
            if (null == list1) {
                throw new BusinessException(-100,"不存在");
            }
            System.out.println("BBBBBBBBBBB");
            CommBean commBean03= new CommBean();
            req.setFuncid("0312");
            req.setOprtno("999");
            req.setTermid("10000001");
            req.setReqdate(new Timestamp(new Date().getTime()));
            commBean03.setReq(req);
            Eapp_SaleBillH salebillh=new Eapp_SaleBillH();
            salebillh.setCredate(new Timestamp(new Date().getTime()));
            salebillh.setDiscmny(new BigDecimal(0.0));
            salebillh.setDrpmny(new BigDecimal(0.0));
            salebillh.setOprtno("999");
            salebillh.setSalchkno("");

            Eapp_SaleBillD salebilldlist = new Eapp_SaleBillD();
            salebilldlist.setActbatchno(15);
            salebilldlist.setDiscmny(new BigDecimal(0.0));
            salebilldlist.setDisctype("M");
            salebilldlist.setGdidentno("325011");
            salebilldlist.setGdname("莱尔斯丹 F");
            salebilldlist.setGdname_j("莱尔斯丹 F");
            salebilldlist.setGdno("00325011");
            salebilldlist.setGdsinfo("");
            salebilldlist.setGiftclassno("");
            salebilldlist.setGiftmode("");
            salebilldlist.setRtntg("");
            salebilldlist.setSalebillno("Y"+Salebillno);
            salebilldlist.setSellprc(new BigDecimal(10.0));
            salebilldlist.setSeqno(9);
            salebilldlist.setSlmny(new BigDecimal(10.0));
            salebilldlist.setSlqty(new BigDecimal(1.0));
            salebilldlist.setSubclsno("325011");
            salebilldlist.setTlmny(new BigDecimal(10.0));
            List<Eapp_SaleBillD>salebilldlist01=new ArrayList<Eapp_SaleBillD>();
            salebilldlist01.add(salebilldlist);
            salebillh.setSalebilldlist(salebilldlist01);

            salebillh.setSalebillno("Y"+Salebillno);
            salebillh.setSalepartno("0020001");
            salebillh.setSlcodetype("00");
            salebillh.setSlmny(new BigDecimal(10.0));
            salebillh.setSlqty(new BigDecimal(1.0));
            salebillh.setTermposno("10000001");
            salebillh.setTermshiftno(9);
            salebillh.setTlmny(new BigDecimal(10.0));
            salebillh.setVdno("001151");
            salebillh.setVdsalerno("");
            salebillh.setVipcardno("");
            salebillh.setVipcls("");
            commBean03.setSalebillh(salebillh);
            System.out.println("CCCCCCCCCCCC");
            Map<String, Object> m3= eappservice.commitsalebill(commBean03);
            logger.info(m3);
            System.out.println("$$"+m3.get("retnote"));
            if(m3.get("retcode").equals(-1)){
                throw new BusinessException(-100, " " + m3.get("retnote"));
            }

            CommBean commBean04= new CommBean();
            req.setFuncid("0411");
            req.setOprtno("999");
            req.setTermid("10000001");
            req.setReqdate(new Timestamp(new Date().getTime()));
            commBean04.setReq(req);
            Eapp_TradeHead tradeh=new Eapp_TradeHead();
            tradeh.setTradeno(Salebillno);
            tradeh.setTermposno("10000001");
            tradeh.setVdno("");
            tradeh.setSalepartno("");
            tradeh.setTermshiftno(9);
            tradeh.setSlcodetype("");
            tradeh.setVipcardno("");
            tradeh.setVipcls("");
            tradeh.setSltype("");
            tradeh.setSlmny(new BigDecimal(""));
            tradeh.setOprtno("");
            tradeh.setCredate("");
            Eapp_TradeBill TradeBill= new Eapp_TradeBill();
            TradeBill.setTradeno(Salebillno);
            TradeBill.setSalebillno("Y"+Salebillno);
            TradeBill.setSlmny(new BigDecimal(123));
            Eapp_TradeBill TradeBill02= new Eapp_TradeBill();
            TradeBill02.setTradeno(Salebillno);
            TradeBill02.setSalebillno("Y"+Salebillno);
            TradeBill02.setSlmny(new BigDecimal(11.1));
            List<Eapp_TradeBill>tradebilllist=new ArrayList<Eapp_TradeBill>();
            tradebilllist.add(TradeBill);
            tradebilllist.add(TradeBill02);
            tradeh.setTradebilllist(tradebilllist);
            commBean04.setTradeh(tradeh);
            System.out.println("DDDDDDDDDD");
            Map<String, Object> m4= eappservice.committrade(commBean04);
            logger.info(m4);
            if(m4.get("retcode").equals(-1)){
                throw new BusinessException(-100, " " + m4.get("retnote"));
            }

            CommBean commBean05= new CommBean();
            req.setFuncid("0501");
            req.setOprtno("999");
            req.setTermid("10000001");
            req.setReqdate(new Timestamp(new Date().getTime()));
            commBean05.setReq(req);
            Eapp_TradePay tradepay= new Eapp_TradePay();
            tradepay.setAccttype("2");
            tradepay.setCardno("");
            tradepay.setChange(new BigDecimal(0.0));
            tradepay.setFeemny(new BigDecimal(0.0));
            tradepay.setFeetag("");
            tradepay.setPaycodena("现金");
            tradepay.setPaycodeno("51");
            tradepay.setPaymny(new BigDecimal(10.0));
            tradepay.setPbmny(new BigDecimal(0.0));
            tradepay.setRefinfo("");
            tradepay.setRestamt(new BigDecimal(0.0));
            tradepay.setSeqno(1);
            tradepay.setSlmny(new BigDecimal(10.0));
            tradepay.setTicketover(new BigDecimal(0.0));
            tradepay.setTradeno(Salebillno);
            commBean05.setTradepay(tradepay);
            Map<String, Object> m5= eappservice.commitpaycode(commBean05);
            logger.info(m5);
            if(m5.get("retcode").equals(-1)){
                throw new BusinessException(-100, " " + m5.get("retnote"));
            }
            CommBean commBean06= new CommBean();
            req.setFuncid("0414");
            req.setOprtno("999");
            req.setTermid("10000001");
            req.setReqdate(new Timestamp(new Date().getTime()));
            commBean06.setReq(req);
            commBean06.setTradeno(Salebillno);
            ResponseInfo<?> info06= eappcontro.eapp_0414(commBean06);
            Map<String, Object> m6= (Map<String, Object>) info06.getData();
            logger.info(m6);
        }
        return null;

    }
}
