<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title></title>

    <style type="text/css">
        /*reset */
        body, dl, dd, h1, h2, h3, h4, h5, h6, p, form, ul, li, ol, input, embed {
            margin: 0;
            padding: 0;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        fieldset, img {
            border: 0;
        }

        address, caption, cite, code, dfn, em, strong, th, var, i {
            font-style: normal;
            font-weight: normal;
        }

        ol, ul {
            list-style: none;
        }

        del, ins {
            text-decoration: none;
        }

        caption, th {
            text-align: left;
        }

        h1, h2, h3, h4, h5, h6 {
            font-size: 100%;
            font-weight: normal;
        }

        input, button, textarea, select {
            font-size: 100%;
        }

        q:before, q:after {
            content: '';
        }

        addr, acronym {
            border: 0;
            font-variant: normal;
        }

        sub, sup {
            font-size: 75%;
            line-height: 0;
            position: relative;
        }

        sub {
            top: -0.5em;
        }

        sup {
            bottom: -0.25em;
        }

        .of {
            overflow: hidden;
        }

        body {
            min-width: 1190px;
        }

        /*clearfix */
        .clearfix:after {
            content: '';
            display: block;
            clear: both;
        }

        .clearfix {
            zoom: 1;
        }

        .clearl {
            clear: left;
        }

        /*img*/
        img {
            vertical-align: top;
            border: none;
        }

        /*a*/
        a {
            outline: none;
            blr: expression(this.onFocus=this.blur());
            text-decoration: none;
            cursor: pointer;
        }

        /*font */
        body {
            font: 12px/1.5 \5b8b\4f53, sans-serif;
            height: 100%;
        }

        .f10 {
            font-size: 10px;
        }

        .f12 {
            font-size: 12px;
        }

        .f14 {
            font-size: 14px;
        }

        .f16 {
            font-size: 16px;
        }

        .f18 {
            font-size: 18px;
        }

        .f20 {
            font-size: 20px;
        }

        .f24 {
            font-size: 24px;
        }

        .f26 {
            font-size: 26px;
        }

        .f30 {
            font-size: 30px;
        }

        .fb {
            font-weight: 700;
        }

        .fn {
            font-weight: 400;
        }

        .t2 {
            text-indent: 2em;
        }

        .lh20 {
            line-height: 20px !important;
        }

        .lh22 {
            line-height: 22px;
        }

        .lh24 {
            line-height: 24px;
        }

        .lh26 {
            line-height: 26px;
        }

        .lh28 {
            line-height: 28px;
        }

        .lh30 {
            line-height: 30px;
        }

        .lh34 {
            line-height: 34px;
        }

        .lh40 {
            line-height: 40px;
        }

        .lh46 {
            line-height: 46px;
        }

        .no_unl {
            text-decoration: none;
        }

        .bold {
            font-weight: bold;
        }

        .normal {
            font-weight: normal;
        }

        /*position */
        .tl {
            text-align: left;
        }

        .tc {
            text-align: center;
        }

        .tr {
            text-align: right;
        }

        .bc {
            margin-left: auto;
            margin-right: auto;
        }

        .fl {
            float: left;
            display: inline;
        }

        .fr {
            float: right;
            display: inline;
        }

        .vm {
            vertical-align: middle;
        }

        .vt {
            vertical-align: top;
        }

        .pr {
            position: relative;
        }

        .pa {
            position: absolute;
        }

        .abs-right {
            position: absolute;
            right: 0;
        }

        .abs-left {
            position: absolute;
            left: 0;
        }

        .hidden {
            visibility: hidden;
        }

        .visible {
            visibility: visible;
        }

        .none {
            display: none;
        }

        /*margin padding */
        .m5 {
            margin: 5px;
        }

        .m10 {
            margin: 10px;
        }

        .m15 {
            margin: 15px;
        }

        .m20 {
            margin: 20px;
        }

        .m25 {
            margin: 25px;
        }

        .m30 {
            margin: 30px;
        }

        .m50 {
            margin: 50px;
        }

        .mt_1 {
            margin-top: -1px;
        }

        .mt2 {
            margin-top: 2px;
        }

        .mt3 {
            margin-top: 3px;
        }

        .mt5 {
            margin-top: 5px;
        }

        .mt10 {
            margin-top: 10px;
        }

        .mt10F {
            margin-top: -10px;
        }

        .mt15 {
            margin-top: 15px;
        }

        .mt20 {
            margin-top: 20px;
        }

        .mt25 {
            margin-top: 25px;
        }

        .mt30 {
            margin-top: 30px;
        }

        .mt50 {
            margin-top: 50px;
        }

        .mr5 {
            margin-right: 5px;
        }

        .mr10 {
            margin-right: 10px;
        }

        .mr15 {
            margin-right: 15px;
        }

        .mr20 {
            margin-right: 20px;
        }

        .mr25 {
            margin-right: 25px;
        }

        .mr30 {
            margin-right: 30px;
        }

        .mr40 {
            margin-right: 40px;
        }

        .mr50 {
            margin-right: 50px;
        }

        .mr55 {
            margin-right: 55px;
        }

        .mb4 {
            margin-bottom: 4px;
        }

        .mb5 {
            margin-bottom: 5px;
        }

        .mb10 {
            margin-bottom: 10px;
        }

        .mb12 {
            margin-bottom: 12px;
        }

        .mb15 {
            margin-bottom: 15px;
        }

        .mb20 {
            margin-bottom: 20px;
        }

        .mb25 {
            margin-bottom: 25px;
        }

        .mb30 {
            margin-bottom: 30px;
        }

        .mb50 {
            margin-bottom: 50px;
        }

        .ml3 {
            margin-left: 3px;
        }

        .ml5 {
            margin-left: 5px;
        }

        .ml_10 {
            margin-left: -10px !important;
        }

        .ml10 {
            margin-left: 10px;
        }

        .ml15 {
            margin-left: 15px;
        }

        .ml20 {
            margin-left: 20px;
        }

        .ml25 {
            margin-left: 25px;
        }

        .ml30 {
            margin-left: 30px;
        }

        .ml36 {
            margin-left: 36px;
        }

        .ml40 {
            margin-left: 40px;
        }

        .ml45 {
            margin-left: 45px;
        }

        .ml50 {
            margin-left: 50px;
        }

        .ml80 {
            margin-left: 80px;
        }

        .p5 {
            padding: 5px;
        }

        .p10 {
            padding: 10px;
        }

        .p15 {
            padding: 15px;
        }

        .p20 {
            padding: 20px;
        }

        .p25 {
            padding: 25px;
        }

        .p30 {
            padding: 30px;
        }

        .p40 {
            padding: 40px;
        }

        .p50 {
            padding: 50px;
        }

        .pt5 {
            padding-top: 5px;
        }

        .pt10 {
            padding-top: 10px;
        }

        .pt13 {
            padding-top: 13px;
        }

        .pt14 {
            padding-top: 14px;
        }

        .pt15 {
            padding-top: 15px;
        }

        .pt20 {
            padding-top: 20px;
        }

        .pt25 {
            padding-top: 25px;
        }

        .pt30 {
            padding-top: 30px;
        }

        .pt40 {
            padding-top: 40px;
        }

        .pt50 {
            padding-top: 50px;
        }

        .pr5 {
            padding-right: 5px;
        }

        .pr10 {
            padding-right: 10px;
        }

        .pr15 {
            padding-right: 15px;
        }

        .pr20 {
            padding-right: 20px;
        }

        .pr25 {
            padding-right: 25px;
        }

        .pr30 {
            padding-right: 30px;
        }

        .pr50 {
            padding-right: 50px;
        }

        .pb5 {
            padding-bottom: 5px;
        }

        .pb10 {
            padding-bottom: 10px;
        }

        .pb12 {
            padding-bottom: 12px;
        }

        .pb15 {
            padding-bottom: 15px;
        }

        .pb20 {
            padding-bottom: 20px;
        }

        .pb25 {
            padding-bottom: 25px;
        }

        .pb30 {
            padding-bottom: 30px;
        }

        .pb50 {
            padding-bottom: 50px;
        }

        .pl5 {
            padding-left: 5px;
        }

        .pl10 {
            padding-left: 10px;
        }

        .pl15 {
            padding-left: 15px;
        }

        .pl20 {
            padding-left: 20px;
        }

        .pl25 {
            padding-left: 25px;
        }

        .pl30 {
            padding-left: 30px;
        }

        .pl50 {
            padding-left: 50px;
        }

        .pl70 {
            padding-left: 70px;
        }

        .w800 {
            width: 800px;
        }

        .m500 {
            min-height: 500px;
        }

        .auto {
            margin: 0 auto;
        }

        /* font family */
        .f_yahei {
            font-family: "\5FAE\8F6F\96C5\9ED1";
        }

        .f_song {
            font-family: "\5b8b\4f53";
        }

        /* text-indent*/
        .f_ind_2em {
            text-indent: 2em;
        }

        /*loading*/
        .loading img {
            width: 93px;
            height: 67px;
        }

        .emptyhint {
            margin-top: 14px !important;
            margin-left: 10px !important;
            left: 0 !important;
            left: -5px \0 !important;
            *left: -5px !important;
            position: absolute !important;
            font: 12px/1.5 !important;
            color: #cbcbcb !important;
            text-align: left !important;
            width: auto !important;
            height: auto !important;
            line-height: 18px !important;
        }

        .login_info .emptyhint {
            margin-top: 19px !important;
            margin-left: 34px !important;
        }

        /*autofill*/
        input:-webkit-autofill {
        }

        /*common*/
        .wrap {
            width: 100%;
            font-family: "\5FAE\8F6F\96C5\9ED1";
        }

        input {
            outline: none;
        }

        .ml3 {
            margin-left: 3px;
        }

        .inner {
            width: 1190px;
            margin: 0 auto;
            font-family: "\5FAE\8F6F\96C5\9ED1";
        }

        .bg {
            background: url(../../images/tourInsurance/bg.jpg) repeat;
        }

        .bg_0 {
            background: #f0f2f5;
        }

        .bg_1 {
            background: #3da06a;
        }

        .bg_2 {
            background: #e6e6e6;
        }

        .bg_3 {
            background-color: #fff;
            z-index: 10;
        }

        .bg_4 {
            background: #fff;
        }

        .bg_5 {
            background: #349b63;
        }

        .bg_6 {
            background: #e2f1e9;
        }

        .bg_7 {
            background: #f0f2f5;
        }

        .bg_8 {
            background: #f6f6f6;
        }

        .bg_foot {
            background: url(../../images/tourInsurance/foot_bg.png) repeat-x #fff;
            height: 7px;
            width: 100%;
            overflow: hidden;
            position: absolute;
            top: -2px;
            left: 0;
        }

        .bg_footer {
            background: #fff;
            position: relative;
        }

        .bg_f {
            background: #fff;
        }

        .green {
            color: #29965b;
        }

        .orange {
            color: #ff7542;
        }

        .gray_light {
            color: #cbcbcb;
        }

        .gray {
            color: #878787;
        }

        .gray_deep {
            color: #666;
        }

        .blue {
            color: #119bf2;
        }

        .red {
            color: #ff0000;
        }

        .bg_mark {
            position: absolute;
            left: 0;
            top: 75px;
            width: 100%;
            height: 8px;
            overflow: hidden;
            background: url(../../images/tourInsurance/bg_mark_t.png) repeat-x;
            z-index: 2;
        }

        /*sp_foot*/
        .foot_fixed {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
        }

        /*all_bg*/
        .search, .search_icon, .corner, .tourism_btn {
            background: url(../../images/tourInsurance/all_bg.png) 0 0 no-repeat;
        }

        /*hearder*/
        .header {
            line-height: 24px;
            padding: 6px 18px 5px 6px;
            width: 1166px;
        }

        .header a {
            padding: 0 6px;
            color: #052f18;
        }

        .header a:hover {
            color: #fff;
        }

        .header_left img {
            margin: 5px 0 0 10px;
        }

        .header_right {
            float: right;
        }

        .search {
            float: left;
            height: 25px;
            width: 177px;
            height: 25px;
            padding: 0 3px;
            overflow: hidden;
        }

        .search input {
            float: left;
            width: 154px;
            height: 23px;
            border: none;
            background: none;
            line-height: 22px;
            text-indent: 2em;
        }

        .search em {
            float: left;
        }

        .search .search_icon {
            float: left;
            width: 12px;
            height: 12px;
            overflow: hidden;
            background-position: 0 -26px;
            padding: 0;
            margin: 7px 0 0 4px;
        }

        /*nav*/
        .logo {
            float: left;
            width: 194px;
            height: 52px;
            background: url(../../images/tourInsurance/logo.png) no-repeat;
            margin: 13px 130px 9px 0;
        }

        .nav {
            position: relative;
            float: right;
            height: 53px;
            margin-top: 22px;
            z-index: 2;
            margin-right: 50px;
        }

        .nav li {
            float: left;
            vertical-align: top;
            margin: 0 10px;
            display: inline;
        }

        .nav li.mark {
            position: absolute;
            height: 5px;
            width: 118px;
            background: #29965b;
            overflow: hidden;
            left: 0;
            top: 48px;
            z-index: 3;
        }

        .nav li a {
            float: left;
            width: 118px;
            height: 53px;
            line-height: 52px;
            text-align: center;
            font-size: 16px;
            color: #383838;
        }

        .nav li.active a, .nav li.select a {
            color: #29965b;
            -webkit-transition-property: color;
            -moz-transition-property: color;
            -ms-transition-property: color;
            -o-transition-property: color;
            transition-property: color;
            -moz-transition-duration: 0.8s;
            -webkit-transition-duration: 0.8s;
            -ms-transition-duration: 0.8s;
            -o-transition-duration: 0.8s;
            transition-duration: 0.8s;
        }

        .user_center {
            float: right;
            width: 108px;
            height: 34px;
            line-height: 28px;
            background: url(../../images/tourInsurance/user_center.png) 4px 7px no-repeat;
            cursor: pointer;
            color: #29965b;
            font-size: 16px;
            text-indent: 30px;
            margin-top: 33px;
            position: relative;
            z-index: 22;
        }

        .center_link {
            color: #29965b;
        }

        .nav.hidden {
            display: none;
        }

        .user_center.hidden {
            display: none;
        }

        /*.user_center a{color:#fff;}*/
        .user_center_list {
            position: absolute;
            width: 120px;
            padding: 2px 0 8px 0;
            background: #fff;
            left: -8px;
            top: 33px;
            z-index: 20;
            display: none;
        }

        .user_center:hover .user_center_list {
            display: block;
        }

        .user_center_list a {
            display: block;
            height: 33px;
            line-height: 34px;
            overflow: hidden;
            text-align: center;
            color: #696969;
            text-indent: 0;
        }

        .user_center_list a:hover {
            color: #29965b;
            background: #f0f2f5;
            -webkit-transition-property: color;
            -moz-transition-property: color;
            -ms-transition-property: color;
            -o-transition-property: color;
            transition-property: color;
            -moz-transition-duration: 0.8s;
            -webkit-transition-duration: 0.8s;
            -ms-transition-duration: 0.8s;
            -o-transition-duration: 0.8s;
            transition-duration: 0.8s;
        }

        /*footer*/
        .footer {
            padding: 41px 0;
            color: #a1a1a1;
            font-size: 14px;
            line-height: 24px;
        }

        .blogroll {
            line-height: 26px;
            font-size: 14px;
            display: inline-block;
            *display: inline;
            zoom: 1;
        }

        .blogroll dt {
            float: left;
        }

        .blogroll dd {
            padding: 0px 7px 0 25px;
            float: left;
            line-height: 26px;
        }

        .blogroll dd.bor_r {
            border-right: 1px dashed #bebebe;
        }

        .blogroll a {
            color: #a1a1a1;
        }

        .footer a:hover {
            color: #29965b;
        }

        .copyright {
            line-height: 26px;
            font-size: 12px;
        }

        .service_hotline {
            font-size: 18px;
            line-height: 39px;
            font-weight: bold;
            color: #797979;
            text-align: center;
        }

        .service_hotline em {
            font-weight: normal;
        }

        /*circle*/
        span.span_1 {
            float: left;
            width: 2px;
            height: 20px;
            background: url(../../images/tourInsurance/circle.png) 0 0 no-repeat;
        }

        span.span_2 {
            float: left;
            padding: 0 5px;
            height: 20px;
            background: url(../../images/tourInsurance/circle.png) 0 -40px repeat-x;
            font-size: 14px;
            line-height: 20px;
            color: #fff;
        }

        span.span_3 {
            float: left;
            width: 2px;
            height: 20px;
            background: url(../../images/tourInsurance/circle.png) 0 -20px no-repeat;
        }

        /*btn*/
        em.em_1 {
            float: left;
            height: 40px;
            width: 2px;
            background: url(../../images/tourInsurance/btn_max_bg.png) no-repeat;
        }

        em.em_2 {
            float: left;
            height: 40px;
            padding: 0 38px;
            background: url(../../images/tourInsurance/btn_max_bg.png) 0 -80px repeat-x;
            font-size: 16px;
            line-height: 40px;
            color: #fff;
        }

        em.em_3 {
            float: left;
            height: 40px;
            width: 2px;
            background: url(../../images/tourInsurance/btn_max_bg.png) 0 -40px no-repeat;
        }

        .submit_btn {
            display: inline-block;
            cursor: pointer;
        }

        .submit_btn:hover em.em_1 {
            background-position: 0 -120px;
        }

        .submit_btn:hover em.em_2 {
            background-position: 0 -200px;
        }

        .submit_btn:hover em.em_3 {
            background-position: 0 -160px;
        }

        .disable {
            cursor: default !important;
        }

        .disable em.em_1, .disable:hover em.em_1 {
            background: url(../../images/tourInsurance/btn_max_bg_dis.png) no-repeat;
        }

        .disable em.em_2, .disable:hover em.em_2 {
            background: url(../../images/tourInsurance/btn_max_bg_dis.png) 0 -80px repeat-x;
            color: #b6b6b6;
        }

        .disable em.em_3, .disable:hover em.em_3 {
            background: url(../../images/tourInsurance/btn_max_bg_dis.png) 0 -40px no-repeat;
        }

        .cancel_common {
            height: 40px;
            cursor: pointer;
        }

        .cancel_common em {
            background: url(../../images/tourInsurance/cancel_common.png) no-repeat;
            float: left;
        }

        .cancel_common em.em_1 {
            background-position: 0 0px;
            width: 3px;
        }

        .cancel_common em.em_2 {
            background-position: 0 -80px;
            background-repeat: repeat-x;
            color: #ababab;
        }

        .cancel_common em.em_3 {
            background-position: 0 -40px;
            width: 3px;
        }

        .cancel_common em.em_1:hover {
            background-position: 0 -120px;
        }

        .cancel_common em.em_2:hover {
            background-position: 0 -200px;
            background-repeat: repeat-x;
        }

        .cancel_common em.em_3:hover {
            background-position: 0 -160px;
        }

        /*input*/
        .input_bg_1 {
            float: left;
            width: 6px;
            height: 46px;
            background: url(../../images/tourInsurance/input_bgs.png) 0 -138px no-repeat;
        }

        .input_bg_2 {
            float: left;
            height: 46px;
            background: url(../../images/tourInsurance/input_bgs.png) 0 -230px repeat-x;
            color: #cbcbcb;
            position: relative;
            overflow: hidden;
        }

        .input_bg_3 {
            float: left;
            width: 6px;
            height: 46px;
            background: url(../../images/tourInsurance/input_bgs.png) 0 -184px no-repeat;
        }

        .input_bg_2 input {
            float: left;
            width: 100%;
            height: 20px;
            line-height: 20px;
            background: none;
            border: none;
            text-indent: 7px;
            font-size: 14px;
            margin: 12px 0;
        }

        .input_unit {
            line-height: 46px;
            color: #666;
            padding: 0 10px 0 5px;
        }

        /*focus*/
        .input_focus .input_bg_1 {
            background-position: 0 0;
        }

        .input_focus .input_bg_2 {
            background-position: 0 -92px;
        }

        .input_focus .input_bg_3 {
            background-position: 0 -46px;
        }

        /*error*/
        .input_error .input_bg_1 {
            background-position: 0 -276px;
        }

        .input_error .input_bg_2 {
            background-position: 0 -368px;
        }

        .input_error .input_bg_3 {
            background-position: 0 -322px;
        }

        /*disable*/
        .disable {
            background-color: #ddd;
        }

        /*ico*/
        .ico_gre, .ico_org {
            background: url(../../images/tourInsurance/ico.png) no-repeat 0 0;
            width: 36px;
            height: 36px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            text-decoration: none;
            margin-right: 10px;
        }

        .ico_org {
            background-position: -57px 0;
        }

        .ico_print, .ico_down {
            height: 22px;
            width: 22px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            background: url(../../images/tourInsurance/ico02.png) no-repeat;
            text-decoration: none;
            margin-right: 7px;
        }

        .ico_down {
            background-position: -29px 0;
        }

        .hover .ico_print {
            background-position: 0px -26px;
        }

        .hover .ico_down {
            background-position: -29px -26px;
        }

        .ico_lignt_good {
            background: url(../../images/tourInsurance/min_ico.png) no-repeat -61px -23px;
            width: 13px;
            height: 13px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            text-decoration: none;
        }

        .ico_good {
            background: url(../../images/tourInsurance/min_ico.png) no-repeat -61px -41px;
            width: 13px;
            height: 13px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            text-decoration: none;
        }

        /*min_ico*/
        .min_ico01, .min_ico02, .min_ico03, .min_ico04, .ico_word {
            background: url(../../images/tourInsurance/min_ico.png) no-repeat;
            width: 13px;
            height: 13px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            text-decoration: none;
            line-height: 100%;
        }

        .min_ico01 {
            background-position: 0 -3px;
        }

        .min_ico02 {
            background-position: -20px -3px;
        }

        .min_ico03 {
            background-position: -40px -3px;
        }

        .min_ico04 {
            background-position: -60px -3px;
        }

        .ico_word {
            background-position: -80px -2px;
            height: 16px;
            width: 16px;
            vertical-align: 2px;
            *vertical-align: -2px;
        }

        /*radio*/
        .radio {
            background: url(../../images/tourInsurance/min_ico.png) no-repeat -20px -22px;
            height: 13px;
            width: 13px;
            display: inline-block;
            *display: inline;
            zoom: 1;
            vertical-align: -2px;
            cursor: pointer;
            margin: 0 5px;
        }

        .radio.focus {
            background-position: -41px -22px;
        }

        .radio.disable {
            background-position: 0 -22px;
        }

        .radio input {
            display: none;
        }

        /*steps*/
        .steps {
            float: right;
            margin: 20px 40px 0 0;
            position: relative;
            z-index: 1;
        }

        .steps li {
            float: left;
            width: 104px;
            text-align: center;
        }

        .steps i {
            display: inline-block;
            display: inline-block;
            width: 20px;
            height: 20px;
            background: url(../../images/tourInsurance/table_icons.png) 0 -20px no-repeat;
            position: relative;
            z-index: 5;
        }

        .steps li.active i {
            background-position: 0 0;
        }

        .steps p {
            color: #bcbcbc;
            line-height: 28px;
        }

        .steps li.active p {
            color: #ff7542;
        }

        .steps strong {
            position: absolute;
            left: 0;
            top: 9px;
            height: 3px;
            overflow: hidden;
            background: #bcbcbc;
        }

        .steps strong.default {
            width: 100%;
            z-index: 2;
        }

        .steps strong.active {
            background: #ff7542;
            z-index: 3;
        }

        .w42 {
            width: 42px;
        }

        .w60 {
            width: 60px;
        }

        .w89 {
            width: 89px;
        }

        .w140 {
            width: 140px;
        }

        .w146 {
            width: 146px;
        }

        .w252 {
            width: 252px;
        }

        .w_sp {
            width: 100%;
        }

        .w780 {
            width: 780px;
        }

        .w905 {
            width: 905px;
        }

        /*checkbox*/
        .checkbox {
            display: block;
            width: 12px;
            height: 12px;
            overflow: hidden;
            background: url(../../images/tourInsurance/min_ico.png) -21px -41px no-repeat; /*position:relative;*/
            margin: 5px;
        }

        .checkbox input {
            display: none;
        }

        .checkbox_checked {
            background-position: -42px -41px;
        }

        .checkbox_disable {
            background-position: 0px -41px;
        }

        /*bottom*/
        .bd_btm {
            border-bottom: 1px solid #e9e9e9;
        }

        /*sub_nav*/
        .sub_nav {
            width: 215px;
            border: 1px solid #eceef1;
            float: left;
            padding-bottom: 20px;
        }

        .sub_nav dl {
            padding-top: 10px;
        }

        .sub_nav dd {
            height: 44px;
            line-height: 44px;
        }

        .sub_nav dt {
            height: 50px;
            line-height: 50px;
            font-size: 20px;
            padding-left: 25px;
            padding-right: 25px;
        }

        .sub_nav a {
            display: block;
            padding-left: 45px;
            font-size: 15px;
            color: #676767;
            overflow: hidden;
        }

        .sub_nav a.focus {
            color: #fff;
            text-decoration: none;
            background-color: #3ea06b;
            -webkit-transition-property: all;
            -moz-transition-property: all;
            -ms-transition-property: all;
            -o-transition-property: all;
            transition-property: all;
            -moz-transition-duration: 0.8s;
            -webkit-transition-duration: 0.8s;
            -ms-transition-duration: 0.8s;
            -o-transition-duration: 0.8s;
            transition-duration: 0.8s;
        }

        .sub_nav a.focus:hover {
            background-color: #3ea06b;
        }

        .sub_nav a:hover {
            background: #edf4f0;
            -webkit-transition-property: all;
            -moz-transition-property: all;
            -ms-transition-property: all;
            -o-transition-property: all;
            transition-property: all;
            -moz-transition-duration: 0.8s;
            -webkit-transition-duration: 0.8s;
            -ms-transition-duration: 0.8s;
            -o-transition-duration: 0.8s;
            transition-duration: 0.8s;
        }

        .sub_nav strong {
            display: block;
            overflow: hidden;
            background: url(../../images/tourInsurance/dashed.gif) repeat-x left bottom;
        }

        /*r_con*/
        .r_con {
            width: 940px;
            margin-left: 245px;
            border: 1px solid #eceef1;
            background: #fff;
            min-height: 600px;
            _height: 600px;
            padding-bottom: 30px;
        }

        .r_cont {
            width: 940px;
            border: 1px solid #eceef1;
            background: #fff;
            min-height: 600px;
            padding-bottom: 30px;
            float: right;
        }

        /*time*/
        .time {
            position: relative;
        }

        .time span.timeIco {
            position: absolute;
            padding-right: 5px;
            top: 10px;
            right: 5px;
            background: url(../../images/tourInsurance/min_ico.png) no-repeat 0 -61px;
            width: 25px;
            height: 25px;
            cursor: pointer;
            z-index: 1;
        }

        .time em.input_bg_2 {
            padding-right: 30px;
        }

        .time input {
            position: absolute;
            z-index: 5;
        }

        /*pager*/
        .pager {
            font-family: Arial;
            font-size: 12px;
            text-align: center;
            vertical-align: baseline;
        }

        .pager a {
            display: inline-block;
            *display: inline;
            zoom: 1;
            height: 24px;
            line-height: 24px;
            background: #eff2f5;
            padding: 0 9px;
            color: #9ba1a7;
            margin: 0 3px;
            border: 1px solid #ebebeb;
        }

        .pager a:hover, .pager a.focus {
            color: #fff;
            background: #3ea06b;
        }

        .pager input {
            height: 24px;
            width: 58px;
            border: 1px solid #ebeff3;
            vertical-align: 8px \9;
            *vertical-align: -11px;
        }

        .pager .next, .pager .prev {
        }

        .pager .next, .pager .next.no:hover {
            background: #eff2f5 url(../../images/tourInsurance/pager.png) no-repeat 44px -76px;
            padding-right: 28px;
            color: #9ba1a7;
        }

        .pager .prev, .pager .prev.no:hover {
            background: #eff2f5 url(../../images/tourInsurance/pager.png) no-repeat 8px 4px;
            padding-left: 28px;
            color: #9ba1a7;
        }

        .pager .next.no, .pager .prev.no {
            cursor: default;
        }

        .pager .next:hover {
            background: #3ea06b url(../../images/tourInsurance/pager.png) no-repeat 44px -117px;
            padding-right: 28px;
        }

        .pager .prev:hover {
            background: #3ea06b url(../../images/tourInsurance/pager.png) no-repeat 8px -36px;
            padding-left: 28px;
        }

        #error_icon {
            background: url(../../images/tourInsurance/mistake.gif) 0 center no-repeat;
        }

        .insure_page {
            background-color: #fff;
            padding-top: 0px;
        }

        .insure_ct {
            border-top: 1px solid #dbdcdc;
            padding-top: 5px;
            overflow: hidden;
            zoom: 1;
        }

        .insure_page a {
            float: left;
            padding-right: 20px;
            color: #dbdcdc;
            font-size: 17px;
            font-family: Arial;
        }

        .insure_page a:hover {
            color: #4a4a4a;
        }

        .insure_page a.focus {
            color: #429e6a;
        }

        .insure_page a.prev, .insure_page a.next {
            color: #a1a1a1;
            font-weight: bold;
            font-family: "\5b8b\4f53";
        }

        .insure_page.w922 {
            width: 922px;
            position: relative;
            top: -2px;
            margin-left: 65px;
        }

        .insure_page.w780 {
            width: 780px;
            position: relative;
            top: -2px;;
            margin-left: 130px;
        }

        /*warn*/
        .war {
            border-top: 5px solid #3d9f6a;
            background: #f5faf7;
            padding: 40px 0;
        }

        .war_box {
            display: inline-block;
            *display: inline;
            zoom: 1;
        }

        .war_bod {
            padding-left: 46px;
            color: #676767;
        }

        label.validate_error {
            float: none !important;
            line-height: 14px !important;
            height: 14px !important;
            margin: 0 !important;
            padding: 0 !important;
            position: relative !important;
            color: #ff0000 !important;
            font-size: 12px !important;
        }

        .error_msg {
            min-height: 24px;
            _height: 24px;
        }

        i.error_msg i.validate_error {
            color: #f00;
        }

        /*common_msg*/
        .common_msg {
            font-family: "\5FAE\8F6F\96C5\9ED1";
        }

        .common_msg dt {
            float: left;
            width: 45px;
            height: 45px;
            background: url(../../images/tourInsurance/common_msg.png) no-repeat;
            margin: 10px 17px 0 0;
        }

        .common_msg dd {
            float: left;
        }

        .common_msg h5 {
            font-size: 22px;
            color: #ff7542;
            line-height: 44px;
            color: #2d9d61;
        }

        .common_msg p {
            font-size: 14px;
            color: #676767;
            line-height: 24px;
            padding-bottom: 20px;
        }

        .common_msg a {
            text-decoration: underline;
            color: #119bf2;
        }

        .common_msg_default dt {
            background-position: 0 -90px;
        }

        .common_msg_error dt {
            background-position: 0 -45px;
        }

        .common_msg_error h5 {
            color: #e94c24;
        }

        /*404*/
        .nofound {
            background: url(../../images/tourInsurance/nofound.jpg) no-repeat;
            width: 1132px;
            height: 374px;
            position: relative;
            margin: 160px auto;
        }

        .nofound p {
            position: absolute;
            top: 130px;
            left: 90px;
            color: #9a9bac;
            font-size: 18px;
            font-family: "\5FAE\8F6F\96C5\9ED1";
        }

        .nofound p a {
            color: #3fa6ef;
        }

        .nofound p a:hover {
            text-decoration: underline;
        }

        .s_break {
            margin: 0 3px;
        }

        .loop_wrap {
            position: relative;
            width: 100%;
            margin: 0;
        }

        .bg_mark_top {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 8px;
            overflow: hidden;
            background: url(../../images/tourInsurance/bg_mark_t.png) repeat-x;
            z-index: 2;
        }

        .nofind {
            color: #8c8d8d;
        }

        .nofind .copyright {
            text-align: center;
            font-size: 12px;
        }

        .nofind .service_hotline {
            text-align: center;
            font-size: 18px;
            color: #9e9f9f;
        }

        /*20140826update  js_layer*/
        .js_layer {
            width: 600px;
            height: 400px;
            position: fixed;
            left: 0;
            top: 100px;
            z-index: 10;
        }

        .js_layer_btn {
            position: absolute;
            width: 38px;
            height: 38px;
            background: url(../../images/tourInsurance/js_layer_btn.png) no-repeat;
            top: -19px;
            right: -19px;
        }

        .js_layer_btn:hover {
            background-position: 0 -38px;
        }

        .js_layer img {
            width: 600px;
            height: 400px;
        }

        /*天窗页*/
        .waitwork {
            width: 1200px;
            height: 537px;
            position: relative;
            background: url(../../images/tourInsurance/waitwork_bg.jpg) no-repeat 0 70px;
            padding: 70px 0;
            margin: 0 auto;
        }

        .waitBtn {
            border: 0;
            position: absolute;
            top: 336px;
            left: 535px;
            width: 218px;
            height: 45px;
            background: url(../../images/tourInsurance/wait_href_img.png) no-repeat;
        }

        .waitBtn:hover {
            background-position: 0 -45px;
        }

        .waitgo {
            position: absolute;
            top: 282px;
            left: 0;
            width: 363px;
            height: 320px;
        }

        .waitgo img {
            width: 363px;
            height: 320px;
            display: block;
        }

        /*天气*/
        .my_weather {
            position: fixed;
            right: 80px;
            bottom: 30px;
            z-index: 100;
            text-decoration: none;
            color: #333;
        }

        .weather_btn {
            position: absolute;
            width: 20px;
            height: 20px;
            top: -15px;
            right: -15px;
            background: url(../../images/tourInsurance/weather_btn.png) no-repeat;
        }

        .weather_btn:hover {
            background-position: 0 -20px;
        }

        .weather {
            width: 170px;
            text-decoration: none;
        }

        #weather_area {
            text-align: center;
            font-family: Arial;
            font-size: 14px;
        }

        #weather_gif {
            width: 125px;
            height: 110px;
        }

        .info_detail {
            border: 2px solid #ac967e;
            padding: 3px 0;
            position: relative;
            z-index: 3;
            background-color: #fff;
        }

        #wea_city {
            color: #0b8ad3;
            text-decoration: underline;
        }

        .wea_btm {
            background: url(../../images/tourInsurance/ewm_bg.png) no-repeat;
            width: 189px;
            padding-top: 12px;
            overflow: hidden;
            zoom: 1;
            margin-left: -10px;
            margin-top: -5px;
            z-index: 1;
            position: relative;
        }

        .wea_btm a {
            background-color: #fff;
            padding: 7px;
            display: block;
            border: 1px solid #d8d8d8;
            text-align: center;
            text-decoration: none;
            overflow: hidden;
            zoom: 1;
        }

        .wea_btm img {
            width: 63px;
            height: 63px;
            float: left;
            border: 0;
        }

        .ewmTxt {
            margin-left: 70px;
        }

        .ewmTxt h3 {
            font-size: 16px;
            color: #50483e;
            text-align: left;
            padding: 0;
            line-height: 22px;
            margin: 0;
            margin-top: 10px;
            text-decoration: none;
        }

        .ewmTxt p {
            font-size: 12px;
            color: #796b5c;
            text-align: left;
            text-decoration: none;
            padding: 0;
            margin: 0;
            margin-top: 5px;
        }

        .info_detail {
            border: 2px solid #ac967e;
            padding: 3px 0;
            background: #f0f2f5;
        }

        .error_select {
            border: 1px solid #ff576b;
            box-shadow: 1px 1px 3px #ffa4b5, -1px -1px 3px #ffa4b5;
        }

        /*新加投保提示*/
        .quiz {
            border: 1px solid #dcdcdc;
            color: #888;
            margin-left: 36px;
            padding: 12px;
            font-size: 12px;
            margin-bottom: 10px;
        }

        .quiz a {
            color: #119bf2;
            text-decoration: none;
        }

        .quiz a:hover {
            text-decoration: underline;
        }

        .xl_upload {
            width: 124px;
            height: 33px;
            overflow: hidden;
            font-size: 30px;
            opacity: 0;
            filter: alpha(opacity:0);
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
            cursor: pointer;
        }

        /*tips*/
        .datawarn {
            background: url(../../images/tourInsurance/tips_L.png) no-repeat;
            position: absolute;
            bottom: 10px;
            left: 0px;
            height: 30px;
            padding-left: 5px;
        }

        .datawarn .dataR {
            background: url(../../images/tourInsurance/tips_R.png) no-repeat right 0;
            padding-right: 5px;
        }

        .datawarn .dataT {
            background: url(../../images/tourInsurance/tips.png) repeat;
            color: #fff;
            font-size: 12px;
            line-height: 30px;
        }

        .datawarn i {
            background: url(../../images/tourInsurance/tips_arr.png) no-repeat;
            width: 10px;
            height: 5px;
            position: absolute;
            bottom: -5px;
            left: 10px;
        }

        /*ttable*/
        .ttable td {
            border: 1px solid #000;
            padding: 5px 0;
        }

        .repair { /*position:relative;*/
            overflow: hidden;
        }

        .repair p {
            float: left;
        }

        /*20150126 width_auto*/
        .width_auto {
            width: auto;
        }

        /*kf*/
        #cs_box {
            width: 120px;
            padding: 8px 1px;
            height: 146px;
            color: #000;
            position: fixed;
            left: 125px;
            top: 50%;
            margin-top: -81px;
            z-index: 1000;
            background: url(../../images/tourInsurance/kf_bg.png) no-repeat;
        }

        .cs_close {
            color: #fff;
            position: absolute;
            right: 10px;
            top: 8px;
            width: 14px;
            height: 15px;
            overflow: hidden;
            background: url(../../images/tourInsurance/kf_close.png) no-repeat;
        }

        .cs_close:hover, .cs_close:active {
            background-color: #ccc
        }

        .cs_title {
            height: 18px;
            line-height: 18px;
            display: block;
            font-size: 14px;
            padding-left: 9px;
        }

        .cs_img {
            width: 120px;
            height: 84px;
            background: url(../../images/tourInsurance/kfqq.png) no-repeat;
            margin: 8px 0;
        }

        .cs_info {
            font-size: 12px;
            margin: 0px 10px;
            overflow: hidden;
            text-align: center;
        }

        .cs_btn {
            display: inline-block;
            width: 92px;
            height: 28px;
            background: #1d9dcd;
            border-radius: 5px;
            font-size: 14px;
            line-height: 28px;
            color: #fff;
            text-align: center;
            margin: 0 14px;
        }

        .cs_btn:active, .cs_btn:hover {
            background-color: #1988b2;
        }

        /*modeBw*/
        .modeBox {
            width: 400px;
            height: 300px;
            position: absolute;
            z-index: 999999;
            left: 50%;
            margin-left: -250px;
            top: 50%;
            margin-top: -200px;
            background: #fff;
            padding: 50px;
        }

        .modeBox h2 {
            line-height: 40px;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #29965b;
        }

        .modeBox div {
            color: #666;
        }

        .modeBrowser {
            text-align: center;
            padding-top: 50px;
        }

        .modeBrowser a {
            display: inline-block;
            *display: inline;
            zoom: 1;
            width: 70px;
            height: 70px;
            margin: 0 20px;
        }

        .modeBrowser a img {
            display: block;
            width: 100%;
            height: 100%;
        }

        .modeMaker {
            width: 100%;
            height: 100%;
            position: absolute;
            z-index: 99999;
            background: #000;
            opacity: 0.8;
            filter: alpha(opacity=80);
            left: 0;
            top: 0;
        }

        /*20150202update*/
        .consult {
            width: 49px;
            height: 120px;
            background: url(../../images/tourInsurance/consult.png) no-repeat;
            position: fixed;
            top: 50%;
            left: -3px;
            margin-top: -60px;
            padding: 0 5px 0 3px;
            z-index: 1111;
        }

        .consult span {
            display: block;
            padding: 7px 10px 3px;
            line-height: 18px;
            color: #e63917;
            font-size: 14px;
        }

        .consult_btn {
            display: block;
            width: 49px;
            height: 44px;
            background: url(../../images/tourInsurance/consult_icon.png) no-repeat;
        }

        .consult_btn:hover {
            background-position: 0 -44px;
        }

        .consult_close {
            display: block;
            text-align: center;
            font-size: 14px;
            line-height: 24px;
            color: #000;
        }

        .consult_close:hover {
            color: #e63917;
        }

        .bxgs {
            text-align: center;
            margin-top: 20px;
        }

        /*20150317update*/
        .jz_bf {
            padding-right: 20px;
        }

        .jz_bf em {
            color: #a1a1a1;
        }

        .jz_bf i {
            text-decoration: line-through;
        }

        .header_right a.logo_tehui1 {
            padding: 0px;
            width: 43px;
            height: 14px;
            margin-top: 5px;
            background: url(../../images/tourInsurance/thblogo2.png) 0 0 no-repeat;
        }

        .header_right a.logo_tehui1:hover {
            padding: 0px;
            width: 43px;
            height: 14px;
            margin-top: 5px;
            background: url(../../images/tourInsurance/thblogo2.png) 0 0 no-repeat;
        }

        /*pay*/
        .war {
            border-top: 5px solid #3d9f6a;
            background: #f5faf7;
            padding: 40px 0 20px;
        }

        .war_box {
            display: inline-block;
            *display: inline;
            zoom: 1;
        }

        .war_bod {
            padding-left: 46px;
            color: #676767;
        }

        span.space {
            color: #d4d8db;
            font-size: 12px;
            margin: 0 3px;
        }

        .pay_opt {
            color: #333;
            padding: 23px 0;
            border-bottom: 1px solid #e7e7e7;
        }

        .pay_opt a, .pay_info_c a {
            color: #1b6fb1;
        }

        .pay_info_c a {
            text-decoration: underline;
        }

        .pay_opt a:hover, .pay_info_c a:hover {
            color: #29965b;
        }

        .pay_info {
            padding-left: 115px;
            padding-bottom: 20px;
            font-family: "\5b8b\4f53";
            background: url(../../images/tourInsurance/dashed.gif) repeat-x left bottom;
        }

        .pay_info_t {
            color: #2d3438;
            font-size: 16px;
            line-height: 20px;
            padding: 5px 0 20px;
        }

        .pay_info_b {
            color: #676767;
            font-size: 14px;
        }

        .pay_info_b th {
            font-weight: bold;
            width: 100px;
            text-align: right;
            padding: 5px 0;
            line-height: 16px;
        }

        .pay_info_b td {
            padding-left: 20px;
            line-height: 16px;
            padding: 5px 0;
        }

        .pay_info_c {
            color: #918f8f;
            padding: 20px 0 20px 38px;
        }

        .pay_tool {
            margin-top: 20px;
            height: 72px;
        }

        .pay_tl.hover {
            background: url(../../images/tourInsurance/pay_tool.png) repeat-x;
            position: fixed;
            top: -70px;
            left: 50%;
            width: 1190px;
            margin-left: -595px;
        }

        .pay_tl {
            height: 60px;
            padding-top: 12px;
        }

        .pay_t {
            width: 870px;
            margin: 0 auto;
        }

        .pay_operate {
            float: left;
            padding: 9px 0;
            line-height: 22px;
            width: 114px;
            vertical-align: baseline;
            margin-right: 12px;
            padding-left: 10px;
        }

        .pay_operate:hover {
            background: url(../../images/tourInsurance/pay_operate.png) no-repeat;
        }

        .pay_tl .fl a span {
            color: #666;
            font-size: 14px;
            text-decoration: underline;
            vertical-align: -2px;
            vertical-align: 0 px\0 \9;
            *vertical-align: 0px;
            color: #29965b;
        }

        .pay_tl .fl a:hover span {
            color: #fff;
        }

        .pay_tl.hover .fl a {
            color: #fff;
        }

        .pay_tl.hover .fl a:hover {
            color: #29965b;
        }

        .pay_tl.hover .fl a span {
            color: #fff;
        }

        .pay_operate:hover .ico_print {
            background-position: 0px -26px;
        }

        .pay_operate:hover .ico_down {
            background-position: -29px -26px;
        }

        .pay_tl.hover em.em_1 {
            background: url(../../images/tourInsurance/btn_max_bg_hover.png) no-repeat;
        }

        .pay_tl.hover em.em_2 {
            background: url(../../images/tourInsurance/btn_max_bg_hover.png) 0 -80px repeat-x;
            color: #29965b;
        }

        .pay_tl.hover em.em_3 {
            background: url(../../images/tourInsurance/btn_max_bg_hover.png) 0 -40px no-repeat;
        }

        .btw150 em.em_2 {
            padding: 0 17px;
        }

        .pay_file {
            position: relative;
            overflow: hidden;
            display: inline-block;
            *display: inlnie;
            zoom: 1;
        }

        .pay_file input {
            filter: alpha(opacity=0);
            opacity: 0;
            position: absolute;
            top: 0;
            right: 0;
            height: 100px;
            width: 500px;
            font-size: 200px;
        }

        .pay_file a {
            color: #1b6fb1 !important;
            font-size: 12px !important;
        }

        .go_tb {
            float: left;
            width: 98px;
            height: 32px;
            text-align: center;
            line-height: 32px;
            color: #fff;
            background: url(../../images/tourInsurance/recharge.png) no-repeat;
        }

        .go_tb:hover {
            background-position: 0 -32px;
        }

        .pay_table_box {
        }

        .pay_table_box table {
            font-size: 14px;
            line-height: 15px;
        }

        .pay_table_box a {
            font-family: 宋体;
            font-size: 14px;
            color: #0066CC;
        }

        .pay_table_box a:link {
            text-decoration: none;
        }

        .pay_table_box a:visited {
            text-decoration: none;
            color: #0066CC;
        }

        .pay_table_box a:hover {
            text-decoration: underline;
            color: #0099CC;
        }

        .pay_table_box a:active {
            text-decoration: none;
        }

        .pay_table_box strong {
            font-weight: bold;
        }

        .pay_table_box td {
            padding-left: 10px;
        }

        .pay_table_box * {
            padding: auto;
            margin: auto;
        }

        .STYLE4 {
            font-size: 26px;
            font-weight: bold;
            font-family: "宋体";
            line-height: 19px;
        }

        .STYLE5 {
            font-family: "宋体";
            font-size: 14px;
            line-height: 20px;
        }

        .STYLE8 {
            color: #000000;
            font-family: "宋体";
            font-size: 14px;
        }

        .STYLE7 {
            font-size: 14px;
            border-top-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-left-style: solid;
            border-top-color: #000000;
            border-left-color: #000000;
        }

        .STYLE10 {
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
        }

        .STYLE11 {
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
        }

        .STYLE12 {
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
        }

        .STYLE14 {
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
        }

        .STYLE15 {
            font-size: 12px;
            border-top-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-left-style: solid;
            border-top-color: #000000;
            border-left-color: #000000;
            font-weight: bold;
        }

        .STYLE16 {
            font-size: 12px;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
            font-weight: bold;
        }

        .STYLE17 {
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
        }

        .STYLE18 {
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: #000000;
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
        }

        .STYLE19 {
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
        }

        .STYLE20 {
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: #000000;
            border-left-width: 1px;
            border-left-style: solid;
            border-left-color: #000000;
            border-right-width: 1px;
            border-right-style: solid;
            border-right-color: #000000;
        }

    </style>
</head>
<body>

<div class="wrap_bg">
    <div class="inner bg_4">
        <div id="printDetail" class="pay_table_box" style="padding-top:50px">

            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td height="50" colspan="2" align="center" style="padding-bottom:20px;"><span class="STYLE4">苏州市危险化学品企业安全生产责任保险投保单</span>
                    </td>
                </tr>
                <tr>
                    <td align="left"><p><span class="STYLE5"><strong>投保提示： </strong></span></p></td>
                    <td align="right"><p><span class="STYLE5"><strong>No：${(applicationFormCode)!} </strong></span></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <p><span class="STYLE5"><strong>
					<ul type="square">
					<li>请您填写投保单前详细阅读保险条款。阅读条款时请您特别注意条款中的保险责任、责任免除、投保人被保险人义务、赔偿处理等内容。
					</li>
					<li>为保障您的合法权益，请您如实、完整、准确地填写本投保单。</li>
					<li>投保信息如有变动请及时办理变更手续。</li>
					</ul>

					 </strong></span></p>
                        <br/></td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">投保人名称</td>
                    <td id="agentName" align="left" class="STYLE7">&nbsp;${(holderName)!}</td>
                    <td width="17%" height="26" align="left" class="STYLE7">联系电话</td>
                    <td colspan="4" id="agentPhone" align="left" class="STYLE11">&nbsp;${(holderLinkTel)!}</td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">投保人地址</td>
                    <td id="agentAddress" width="24%" align="left" class="STYLE7">&nbsp;${(holderLocation)!}</td>
                    <td width="17%" height="26" align="left" class="STYLE7">邮编</td>
                    <td colspan="4" id="zipCode" align="left" class="STYLE11">&nbsp;${(holderZipCode)!}</td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">被保险人名称</td>
                    <td id="agentAddress" align="left" class="STYLE7">&nbsp;${(insuredName)!}</td>
                    <td width="17%" align="left" class="STYLE7">联系电话：</td>
                    <td colspan="4" id="agentTel" align="left" class="STYLE11">&nbsp;${(insuredLinkTel)!}</td>
                </tr>
                <tr style="line-height:20px">
                    <td width="17%" height="26" align="left" class="STYLE7">被保险人地址</td>
                    <td id="agentAddress" colspan="5" align="left" class="STYLE7">
                        <input type="checkbox" name="a" <#if isHolder=='Y'>checked</#if>/>与投保人一致
                        <div>
                            <input type="checkbox" name="a" <#if isHolder=='N'>checked</#if>/>不同于投保人
                            <#if isHolder=='N'>，请列明:地址 ${(insuredLocation)!}
                                &nbsp;&nbsp;&nbsp;邮编 ${(insuredZipCode)!}</#if>
                        </div>
                    </td>
                </tr>
                <tr style="line-height:20px">
                    <td width="17%" height="26" align="left" class="STYLE7">承保场所范围</td>
                    <td id="agentAddress" colspan="5" align="left" class="STYLE11">
                        &nbsp;被保险人生产、经营区域（包括厂区、办公区、加油站等固定场所）以及确定或可能会受到事故影响的周边区域<br/>
                        &nbsp;注：本承保场所范围仅适用于保险条款第五条。
                    </td>
                </tr>

                <tr>
                    <td height="26" align="left" class="STYLE7">
                        经营性质
                    </td>

                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">
                        <!-- 根据选择的是什么展示什么文字 -->
										<#if managerType=='A'>
										<span>个体/民营企业</span>
                                        <#elseif managerType=='B'>
										<span>国有企业</span>
                                        <#elseif managerType=='C'>
										<span>三资企业</span>
                                        <#else>
										<span>其他</span>
                                        </#if>
                    </td>
                </tr>
                <tr>
                    <td height="26" align="left" class="STYLE7">
                        经营范围
                    </td>

                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">
                        <!-- 根据传入的是什么展示什么文字 -->
                    ${businessScope}
                    </td>
                </tr>
                <tr>
                    <td height="26" align="left" class="STYLE7">企业营业执照统一社会信用代码</td>
                    <td id="agentLicence" colspan="5" align="center" class="STYLE7">
                        &nbsp;<span>${(threeInOneCode)!}</span></td>
                </tr>
                <tr>
                    <td height="26" align="left" class="STYLE7">
                        行业类型
                    </td>

                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">
                        <!-- 根据选择的是什么展示什么文字 -->

                        <#if highestRiskFactor=='M1201'>
                        <span>危险化学品/生产</span>
                        <#elseif highestRiskFactor== 'M1202'>
                        <span>危险化学品/使用</span>
                        <#elseif highestRiskFactor== 'M120416'>
                        <span>危险化学品储存/经营/危险化学品储存</span>
                        <#elseif highestRiskFactor== 'M120419'>
                        <span>危险化学品/经营/带储存经营</span>
                        <#elseif highestRiskFactor== 'H01'>
                        <span>化工/生产</span>
                        </#if>

                    </td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">从业人员数量</td>
                    <td colspan="5" align="center" class="STYLE11">&nbsp;${(employedNum)!}人</td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">产业规模</td>
                    <td colspan="5" align="center" class="STYLE11">&nbsp;
                     <#if scaleCoefficient=='20DT623009'>
                        <span>0-1000万元（不含）</span>
                     <#elseif scaleCoefficient== '20DT381184'>
                        <span>1000-10000万元（不含）</span>
                     <#elseif scaleCoefficient== '20DT107718'>
                        <span>10000-30000万元（不含）</span>
                     <#elseif scaleCoefficient== '20DT646817'>
                        <span>30000万元及以上</span>
                     </#if>

                    </td>
                </tr>
                <tr>
                    <td height="26" align="left" class="STYLE7">
                        重点监管工艺
                    </td>

                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">
                        <!-- 根据选择的是什么展示什么文字 -->
                        <#if supervisionProcess=='20DT683706'>
                        <span>不存在</span>
                        <#elseif supervisionProcess=='20DT101933'>
                        <span>存在</span>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td height="26" align="left" class="STYLE7">
                        企业风险
                    </td>

                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">
                        <!-- 根据选择的是什么展示什么文字 -->
                        <#if riskFactorList=='20DT318140'>
                        <span>蓝色企业</span>
                        <#elseif riskFactorList== '20DT281876'>
                        <span>黄色企业</span>
                        <#elseif riskFactorList== '20DT947090'>
                        <span>橙色企业</span>
                        <#elseif riskFactorList== '20DT969446'>
                        <span>红色企业</span>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">重大危险源级别</td>
                    <td colspan="5" align="center" class="STYLE11" style="padding:10px 0px;">

                        <#if majorHazardSources=='1021'>
                            <span>无重大危险源</span>
                        <#elseif majorHazardSources== '1022'>
                        <span>一级</span>
                        <#elseif majorHazardSources== '1023'>
                        <span>二级</span>
                        <#elseif majorHazardSources== '1024'>
                        <span>三级</span>
                        <#elseif majorHazardSources== '1025'>
                        <span>四级</span>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td width="17%" height="26" align="left" class="STYLE7">安全生产标准化建设</td>
                    <td colspan="6" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td><input type="checkbox" name="a"
                                           <#if productionSafetyStandard=='20DT151376'>checked</#if>/>&nbsp;否
                                </td>
                                <td><input type="checkbox" name="a"
                                           <#if productionSafetyStandard!='20DT151376'>checked</#if>/>&nbsp;是，通过
                                </td>
                                <td>
                                    （<input type="checkbox" name="a"
                                            <#if productionSafetyStandard=='20DT249743'>checked</#if>/>&nbsp;一级
                                    <input type="checkbox" name="a"
                                           <#if productionSafetyStandard=='20DT432939'>checked</#if>/>&nbsp;二级
                                    <input type="checkbox" name="a"
                                           <#if productionSafetyStandard=='20DT809413'>checked</#if>/>&nbsp;三级）
                                    安全生产标准化验收
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr style="line-height: 20px;">
                    <td width="17%" height="26" align="left" class="STYLE7">事故记录</td>
                    <td colspan="5" class="STYLE11" align="center">
                        <!-- 根据选择的展示出来 -->
                        <#if threeYearAccident=='20DT530393'>
                        <span>投保前1年度没有发生生产安全事故</span>
                        <#elseif threeYearAccident== '20DT701979'>
                        <span><br/>连续2年没有发生生产安全事故</span>
                        <#elseif threeYearAccident== '20DT332693'>
                        <span><br/>连续3年及以上没有发生生产安全事故</span>
                        <#elseif threeYearAccident== '20DT484100'>
                        <span><br/>投保前1年度发生一般生产安全事故</span>
                        <#elseif threeYearAccident== '20DT646471'>
                        <span><br/>投保前1年度发生较大生产安全事故</span>
                        <#elseif threeYearAccident== '20DT753508'>
                        <span><br/>投保前1年度发生重大及以上生产安全事故</span>
                        </#if>
                    </td>
                </tr>
                <tr style="line-height: 20px;">
                    <td width="17%" height="26" align="left" class="STYLE7">安全生产事前处罚情况</td>
                    <td colspan="6" class="STYLE11" align="center">
                        <#if enforcementRecords=='20DT679029'>
                            <span>投保前1年度没有受到安全生产事前处罚</span>
                        <#elseif enforcementRecords== '20DT142055'>
                        <span><br/>连续2年没有受到安全生产事前处罚</span>
                        <#elseif enforcementRecords== '20DT843990'>
                        <span><br/>连续3年及以上没有受到安全生产事前处罚</span>
                        <#elseif enforcementRecords== '20DT142581'>
                        <span><br/>投保前1年度受到1次安全生产事前处罚</span>
                        <#elseif enforcementRecords== '20DT510215'>
                        <span><br/>投保前1年度受到2次安全生产事前处罚</span>
                        <#elseif enforcementRecords== '20DT825573'>
                        <span><br/>投保前1年度受到3次及以上安全生产事前处罚</span>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
            <!--2018.11.14 新增 start-->
            <table width="100%" align="center" cellpadding="0" cellspacing="0">

                <tbody>
                <tr>
                    <td width="30%" colspan="5" height="35" align="center" valign="middle" class="STYLE16"><strong>基本险责任限额（必选）</strong>
                    </td>
                </tr>

                <tr>
                    <td rowspan="4" class="STYLE7" align="center">
                        <strong>责任限额</strong>
                    </td>
                    <td height="50" class="STYLE7" align="center">每次事故每人死亡残疾（万元）</td>
                    <td class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <#if swLimit=='20XEZ220185'>
                                    <td align="center"> &nbsp;50万</td></#if>
                                <#if swLimit=='20XEZ198687'>
                                    <td align="center"> &nbsp;60万</td></#if>
                                <#if swLimit=='20XEZ562951'>
                                    <td align="center"> &nbsp;70万</td></#if>
                                <#if swLimit=='20XEZ584916'>
                                    <td align="center"> &nbsp;80万</td></#if>
                                <#if swLimit=='20XEZ870375'>
                                    <td align="center"> &nbsp;90万</td></#if>
                                <#if swLimit=='20XEZ402319'>
                                    <td align="center"> &nbsp;100万</td></#if>
                                <#if swLimit=='20XEZ255003'>
                                    <td align="center"> &nbsp;110万</td></#if>
                                <#if swLimit=='20XEZ327988'>
                                    <td align="center"> &nbsp;120万</td></#if>
                                <#if swLimit=='20XEZ498028'>
                                    <td align="center"> &nbsp;130万</td></#if>
                                <#if swLimit=='20XEZ738938'>
                                    <td align="center"> &nbsp;140万</td></#if>
                                <#if swLimit=='20XEZ896647'>
                                    <td align="center"> &nbsp;150万</td></#if>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="50" class="STYLE7" align="center">每次事故责任限额（万元）</td>
                    <td class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <!-- 根据选的编码 控制谁展示谁不展示 -->

                            <#if limit1=='20XEZ266274'>
                                <td align="center">300万</td></#if>
                            <#if limit1=='20XEZ105906'>
                                <td align="center">400万</td></#if>
                            <#if limit1=='20XEZ691012'>
                                <td align="center">500万</td></#if>
                            <#if limit1=='20XEZ380307'>
                                <td align="center">600万</td></#if>
                            <#if limit1=='20XEZ572069'>
                                <td align="center">700万</td></#if>
                            <#if limit1=='20XEZ355547'>
                                <td align="center">800万</td></#if>
                            <#if limit1=='20XEZ760852'>
                                <td align="center">900万</td></#if>
                            <#if limit1=='20XEZ510028'>
                                <td align="center">1000万</td></#if>
                            <#if limit1=='20XEZ836835'>
                                <td align="center">1100万</td></#if>
                            <#if limit1=='20XEZ350883'>
                                <td align="center">1200万</td></#if>
                            <#if limit1=='20XEZ937656'>
                                <td align="center">1300万</td></#if>
                            <#if limit1=='20XEZ764196'>
                                <td align="center">1400万</td></#if>
                            <#if limit1=='20XEZ359132'>
                                <td align="center">1500万</td></#if>
                            <#if limit1=='20XEZ410033'>
                                <td align="center">1600万</td></#if>
                            <#if limit1=='20XEZ920441'>
                                <td align="center">1700万</td></#if>
                            <#if limit1=='20XEZ809278'>
                                <td align="center">1800万</td></#if>
                            <#if limit1=='20XEZ472891'>
                                <td align="center">1900万</td></#if>
                            <#if limit1=='20XEZ650521'>
                                <td align="center">2000万</td></#if>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="50" class="STYLE7" align="center">累计责任限额</td>
                    <td class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td><input type="checkbox" name="a" <#if limit2=='20XEZ260415'>checked</#if>/>每次事故责任限额的1倍
                                </td>
                                <td><input type="checkbox" name="a" <#if limit2=='20XEZ965625'>checked</#if>/>每次事故责任限额的2倍
                                </td>
                                <td><input type="checkbox" name="a" <#if limit2=='20XEZ901142'>checked</#if>/>每次事故责任限额的3倍
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td colspan="5" height="50" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <td>每次事故及累计事故抢险救援及医疗救护费用责任限额：累计责任限额的20%</td>
                            </tr>
                            <tr>
                                <td>每次事故及累计事故鉴定费用责任限额：累计责任限额的20%</td>
                            </tr>
                            <tr>
                                <td>每次事故及累计法律费用责任限额：累计责任限额的20%</td>
                            </tr>
                            <tr>
                                <td>每次事故财产损失责任限额：同每次事故责任限额</td>
                            </tr>
                            <tr>
                                <td>每次事故每人医疗费用责任限额：每次事故每人死亡残疾责任限额的50%</td>
                            </tr>
                            <tr>
                                <td>每次事故每人随身携带财产损失责任限额：每次事故每人死亡残疾责任限额的5%</td>
                            </tr>
                            <tr>
                                <td>
                                    注：保险人对实际发生的事故发生地适用的工伤保险诊疗项目目录、工伤保险药品目录和工伤保险住院服务标准以外必要的、合理的医疗费、康复费，按照费用金额的50%在每次事故每人医疗费用责任限额进行赔偿。
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </td>
                </tr>


                </tbody>
            </table>
            <table width="100%" align="center" cellpadding="0" cellspacing="0">

                <tbody>
                <tr>
                    <td width="30%" colspan="5" height="35" align="center" valign="middle" class="STYLE16"><strong>附加险（可选）</strong>
                    </td>
                </tr>

                <tr>
                    <!-- <td rowspan="2" class="STYLE7" align="center">
                        <strong>责任限额</strong>
                    </td> -->
                    <!-- <td height="50" class="STYLE7" align="center">每次事故每人死亡残疾（万元）</td> -->
                    <td colspan="2" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height: 30px;">
                            <tbody>
                            <tr>

                                <td><input type="checkbox" name="fujia"
                                           <#if riskCodes?contains( "20XZ386997")>checked</#if>/>&nbsp;附加从业人员责任保险
                                </td>
                                <td><input type="checkbox" name="fujia"
                                           <#if riskCodes?contains( "20XZ720637")>checked</#if>/>&nbsp;附加24小时补偿责任保险
                                </td>
                                <td><input type="checkbox" name="fujia"
                                           <#if riskCodes?contains( "20XZ370470")>checked</#if>/>&nbsp;附加法定传染病责任保险
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="50" class="STYLE7" align="center">附加险责任限额</td>
                    <td colspan="5" height="50" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <td>附加从业人员责任保险、附加24小时补偿责任保险的各项责任限额同基本险对应责任限额。</td>
                            </tr>
                            <tr>
                                <td>附加法定传染病责任保险：</td>
                            </tr>
                            <tr>
                                <td>每次事故每人传染病责任限额：基本险中每人责任限额的20%</td>
                            </tr>
                            <tr>
                                <td>每次事故每人传染病医疗费用责任限额：每次事故每人传染病责任限额10%</td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td class="STYLE7" align="center">
                        <strong>保险费<br/>(人民币元)</strong>
                    </td>
                    <td colspan="2" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <td>(小写)：￥<em id="totalFee">${(prem)!}元</em></td>
                            </tr>
                            <tr>
                                <td>(大写)：<em id="capitalFee">${(premChi)!}</em><br/>(保费以最终签发的保险单为准)</td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="30" class="STYLE7" align="center"><strong>保险期间</strong></td>
                    <td colspan="2" class="STYLE11" align="left">
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <td>
                                    共${(term)!} 天，自${(startDate)!}零时起，至${(endDate)!}二十四时止 <br/>(保险期间以最终签发的保险单为准)
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr style="line-height: 30px;">
                    <td class="STYLE7" align="center">
                        <strong>保险条款</strong>
                    </td>
                    <td colspan="2" class="STYLE11" align="left">
                        苏州市危险化学品行业安全生产责任保险条款（详见附件）
                    </td>
                </tr>
                <#if companyCode == '0001300052.00286'>
                <tr>
                    <!--  这个要根据保险公司编码去判断了 -->
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300052.00286 -->
                                <td>户名：工商银行阊胥路支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中国太平洋财产保险股份有限公司苏州分公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102020109009014304</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300049.00030'>
                <tr>

                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300049.00030 -->
                                <td>户名：工行苏州道前支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中国平安财产保险股份有限公司苏州分公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102020209000489684</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300053.00367'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <!-- 0001300053.00367 -->
                            <tr>
                                <td>户名：国农业银行苏州市石路支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中华联合财产保险股份有限公司苏州中心支公司</td>
                            </tr>
                            <tr>
                                <td>账号：10553801040005128</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>


                </tr>
                <#elseif companyCode == '0001300033.00049'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <!-- 0001300033.00049 -->
                            <tr>
                                <td>户名：工行平江支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：太平财产保险有限公司苏州分公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102020409000407857</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300056.00013'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300056.00013 -->
                                <td>户名：中国银行苏州三元支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中银保险有限公司苏州分公司</td>
                            </tr>
                            <tr>
                                <td>账号：483258243436</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300041.00037'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300041.00037 -->
                                <td>户名：中国工商银行股份有限公司苏州道前支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：阳光财产保险股份有限公司苏州中心支公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102020209000506321</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300051.00288'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300051.00288 -->
                                <td>户名：中国工商银行苏州分行苏州留园支行营业部</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中国人寿财产保险股份有限公司苏州市中心支公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102021019000460036</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300028.00004'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300028.00004 -->
                                <td>户名：瑞穗银行（中国）有限公司苏州分行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：日本财产保险（中国）有限公司江苏分公司</td>
                            </tr>
                            <tr>
                                <td>账号：38001110714100098</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300048.00052'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300048.00052 -->
                                <td>户名：工行苏州分行营业部</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中国大地财产保险股份有限公司苏州中心支公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102020609000423712</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>

                </tr>
                <#elseif companyCode == '0001300050.00210'>
                <tr>
                    <td colspan="3" class="STYLE11" align="left">
                        <span style="color:#333">保费支付：请将保险费划账至苏州市危险化学品企业安全生产责任保险项目保险费专收账户</span>
                        <table width="100%" cellpadding="0" cellspacing="0" style="line-height:30px">
                            <tbody>
                            <tr>
                                <!-- 0001300050.00210 -->
                                <td>户名：中国工商银行苏州新区支行</td>
                            </tr>
                            <tr>
                                <td>开户行名称：中国人民财产保险股份有限公司苏州市分公司</td>
                            </tr>
                            <tr>
                                <td>账号：1102021119000379680</td>
                            </tr>
                            </tbody>
                        </table>
                        <span style="color:#333">为保证保险人在拟定的保险起期开始承担保险责任，请您在保险起期前5个工作日办理投保手续、提交投保资料并完成保费的支付；保险费到达苏州市危险化学品企业安全生产责任保险项目保险费专收账户隔日（即第三日）零时，保险公司签发正式保单。如您未能按时办理投保手续或保险费未能按时到账，导致保险合同未在拟定的保险起期生效，发生保险事故，保险人不承担保险责任。</span>
                    </td>
                </tr>
                </#if>
                <tr>
                    <td height="50" colspan="3" class="STYLE11" align="left">
                        <strong>投保人声明：</strong><br>
                        &nbsp;&nbsp;&nbsp;&nbsp;保险人已向本投保人提供并详细介绍了《佛山市安全生产责任保险条款》，并对其中免除保险人责任的条款(包括但不限于责任免除、免赔额、免赔率、比例赔付或者给付等免除或者减轻保险人责任的条款）、投保人被保险人义务、赔偿处理、其他事项以及本保险合同中保险费支付、告知事项的内容做了明确说明，本投保人已充分理解并接受上述内容，同意以此作为订立保险合同的依据。上述所填写的内容均属实。
                        <div style="margin-left:80%">
                            <strong> 投保人签章：</strong>

                            <br><br>

                            &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                            <strong> 年　　 月 　　日</strong>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td height="26" class="STYLE7" align="center">
                        纸质保单：<input type="checkbox" name="a" <#if policyKind=='01'>checked</#if>/>需要 <input
                            type="checkbox" name="a" <#if policyKind=='00'>checked</#if>/>不需要
                    </td>
                    <td colspan="2" class="STYLE11" align="left">
                        邮寄地址：<input type="checkbox" name="a" <#if mailType=='00'>checked</#if>/>投保人地址 <input
                            type="checkbox" name="a" <#if mailType=='01'>checked</#if>/>被保险人地址 <input type="checkbox"
                                                                                                      name="a"
                                                                                                      <#if mailType=='02'>checked</#if>/>其它地址____${(mailAddress)!}
                        ______________
                    </td>
                </tr>
                <tr>
                    <td height="26" colspan="3" class="STYLE11" align="left">
                        投保机构联系人：${(linkPerson)!}
                        &nbsp;&nbsp;传真：${(fax)!}
                        &nbsp;&nbsp;邮箱：${(linkEmail)!}
                        &nbsp;&nbsp;手机：${(linkPhone)!}
                    </td>
                </tr>
                <tr>
                    <td height="26" colspan="3" class="STYLE14" align="left">保险公司复核人： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;　　　　　　　　　　复核日期：</td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">尊敬的客户，您可以通过以下方式联系我们： 苏州安全网<a href="#">网址</a>；邮箱chinatourins@jiangtai.com</td>
                </tr>
                <tr>
                    <td align="center">
                        24小时客户服务专线400-616-1188
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>
</div>

</body>
</html>