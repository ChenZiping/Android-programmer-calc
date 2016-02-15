package cf.chenziping.programmercalc;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.MenuItem;
import android.widget.*;

import java.math.BigInteger;
import java.net.*;
import java.util.*;


public class MainActivity extends Activity implements View.OnClickListener{

    private long exitTime = 0;
    ProgressDialog updateDialog;
    private static final String FeedbackURL = "http://www.chenziping.cf/program/feedback/index.php";

    HorizontalScrollView hsv;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnA;
    Button btnB;
    Button btnC;
    Button btnD;
    Button btnE;
    Button btnF;
    Button btnDel;
    Button btnCE;
    Button btnClear;
    Button btnPlus;
    Button btnMin;
    Button btnMul;
    Button btnDiv;
    Button btnAnd;
    Button btninOr;
    Button btnexOr;
    Button btnUnary;
    Button btnlS;
    Button btnrS;
    Button btnEqu;
    Button btnOver;
    RadioButton btnBin;
    RadioButton btnOct;
    RadioButton btnDec;
    RadioButton btnHex;
    TextView tvInput;
    TextView tvResult;

    int SystemNum = 10;//进制数
    boolean isClickEqu = false;//判断是否按了“=”按钮
    boolean Vibratable = true;
    public Vibrator vibrator;
    TimerTask task;
    BigInteger bi;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, getVersionName(),Toast.LENGTH_LONG).show();
        hsv=(HorizontalScrollView)findViewById(R.id.hsv);
        btn0=(Button)findViewById(R.id.btn_0);
        btn1=(Button)findViewById(R.id.btn_1);
        btn2=(Button)findViewById(R.id.btn_2);
        btn3=(Button)findViewById(R.id.btn_3);
        btn4=(Button)findViewById(R.id.btn_4);
        btn5=(Button)findViewById(R.id.btn_5);
        btn6=(Button)findViewById(R.id.btn_6);
        btn7=(Button)findViewById(R.id.btn_7);
        btn8=(Button)findViewById(R.id.btn_8);
        btn9=(Button)findViewById(R.id.btn_9);
        btnA=(Button)findViewById(R.id.btn_A);
        btnB=(Button)findViewById(R.id.btn_B);
        btnC=(Button)findViewById(R.id.btn_C);
        btnD=(Button)findViewById(R.id.btn_D);
        btnE=(Button)findViewById(R.id.btn_E);
        btnF=(Button)findViewById(R.id.btn_F);
        btnDel=(Button)findViewById(R.id.btn_delete);
        btnCE=(Button)findViewById(R.id.btn_CE);
        btnClear=(Button)findViewById(R.id.btn_clear);
        btnEqu=(Button)findViewById(R.id.btn_equal);
        btnPlus=(Button)findViewById(R.id.btn_plus);
        btnMin=(Button)findViewById(R.id.btn_minus);
        btnMul=(Button)findViewById(R.id.btn_multiply);
        btnDiv=(Button)findViewById(R.id.btn_divide);
        btnAnd=(Button)findViewById(R.id.btn_and);
        btninOr=(Button)findViewById(R.id.btn_in_or);
        btnexOr=(Button)findViewById(R.id.btn_ex_or);
        btnUnary=(Button)findViewById(R.id.btn_unary);
        btnlS=(Button)findViewById(R.id.btn_left_shift);
        btnrS=(Button)findViewById(R.id.btn_right_shift);
        btnOver=(Button)findViewById(R.id.btn_overthrow);
        btnBin=(RadioButton)findViewById(R.id.btn_bin);
        btnOct=(RadioButton)findViewById(R.id.btn_oct);
        btnDec=(RadioButton)findViewById(R.id.btn_dec);
        btnHex=(RadioButton)findViewById(R.id.btn_hex);
        tvInput=(TextView)findViewById(R.id.input);
        tvResult=(TextView)findViewById(R.id.result);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        //btnDel.setOnClickListener(this);
        btnCE.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEqu.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMin.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnAnd.setOnClickListener(this);
        btninOr.setOnClickListener(this);
        btnexOr.setOnClickListener(this);
        btnUnary.setOnClickListener(this);
        btnlS.setOnClickListener(this);
        btnrS.setOnClickListener(this);
        btnOver.setOnClickListener(this);
        btnBin.setOnClickListener(this);
        btnOct.setOnClickListener(this);
        btnDec.setOnClickListener(this);
        btnHex.setOnClickListener(this);
        tvInput.setOnClickListener(this);
        tvResult.setOnClickListener(this);
        btn2.setClickable(true);
        btn3.setClickable(true);
        btn4.setClickable(true);
        btn5.setClickable(true);
        btn6.setClickable(true);
        btn7.setClickable(true);
        btn8.setClickable(true);
        btn9.setClickable(true);
        btnA.setClickable(false);
        btnB.setClickable(false);
        btnC.setClickable(false);
        btnD.setClickable(false);
        btnE.setClickable(false);
        btnF.setClickable(false);

        btnDel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    task = new TimerTask(){
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            had.sendMessage(message);
                        }
                    };
                    timer = new Timer(true);
                    timer.schedule(task,0, 400); //延时1000ms后执行，1000ms执行一次
                }
                else if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(timer != null) timer.cancel();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(Vibratable) vibrator.vibrate(100);
        switch (v.getId()) {
            case R.id.btn_bin:
                if(!tvInput.getText().toString().equals("")) {
                    try {
                        tvResult.setText(Calc(tvInput.getText().toString()));
                    }
                    catch (Exception e){
                        Toast.makeText(this,"算式错误" + e,Toast.LENGTH_SHORT).show();
                    }
                    tvInput.setText(null);
                }
                btn2.setClickable(false);
                btn3.setClickable(false);
                btn4.setClickable(false);
                btn5.setClickable(false);
                btn6.setClickable(false);
                btn7.setClickable(false);
                btn8.setClickable(false);
                btn9.setClickable(false);
                btnA.setClickable(false);
                btnB.setClickable(false);
                btnC.setClickable(false);
                btnD.setClickable(false);
                btnE.setClickable(false);
                btnF.setClickable(false);
                try {
                    //String text = Integer.toBinaryString(Integer.parseInt(tvResult.getText().toString(), SystemNum));
                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                    Long DecNum = bi.longValue();
//                    Toast.makeText(this,DecNum.toString(),Toast.LENGTH_LONG).show();
                    String text = Long.toBinaryString(DecNum);
                    tvResult.setText(text);
                }
                catch (Exception e){
                    Toast.makeText(this,"无法转换进制" + e,Toast.LENGTH_SHORT).show();
                }
                SystemNum = 2;
                break;

            case R.id.btn_oct:
                if(!tvInput.getText().toString().equals("")) {
                    try {
                        tvResult.setText(Calc(tvInput.getText().toString()));
                    }
                    catch (Exception e){
                        Toast.makeText(this,"算式错误" + e,Toast.LENGTH_SHORT).show();
                    }
                    tvInput.setText(null);
                }
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);
                btn5.setClickable(true);
                btn6.setClickable(true);
                btn7.setClickable(true);
                btn8.setClickable(false);
                btn9.setClickable(false);
                btnA.setClickable(false);
                btnB.setClickable(false);
                btnC.setClickable(false);
                btnD.setClickable(false);
                btnE.setClickable(false);
                btnF.setClickable(false);
                try {
                    //String text = Integer.toOctalString(Integer.parseInt(tvResult.getText().toString(), SystemNum));
//                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
//                    String text = bi.toString(8);
//                    Integer.toOctalString(bi.intValue());
                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                    Long DecNum = bi.longValue();
//                    Toast.makeText(this,DecNum.toString(),Toast.LENGTH_LONG).show();
                    String text = Long.toOctalString(DecNum);
                    tvResult.setText(text);
                }
                catch (Exception e){
                    Toast.makeText(this,"无法转换进制" + e,Toast.LENGTH_SHORT).show();
                }
                SystemNum = 8;
                break;

            case R.id.btn_dec:
                if(!tvInput.getText().toString().equals("")) {
                    try {
                        tvResult.setText(Calc(tvInput.getText().toString()));
                    }
                    catch (Exception e){
                        Toast.makeText(this,"算式错误" + e,Toast.LENGTH_SHORT).show();
                    }
                    tvInput.setText(null);
                }
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);
                btn5.setClickable(true);
                btn6.setClickable(true);
                btn7.setClickable(true);
                btn8.setClickable(true);
                btn9.setClickable(true);
                btnA.setClickable(false);
                btnB.setClickable(false);
                btnC.setClickable(false);
                btnD.setClickable(false);
                btnE.setClickable(false);
                btnF.setClickable(false);
                try {
                    //String text = String.valueOf(Integer.parseInt(tvResult.getText().toString(), SystemNum));
//                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
//                    String text = bi.toString();
                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                    Long DecNum = bi.longValue();
//                    Toast.makeText(this,DecNum.toString(),Toast.LENGTH_LONG).show();
                    String text = DecNum.toString();
                    tvResult.setText(text);
                }
                catch (Exception e){
                    Toast.makeText(this,"无法转换进制" + e,Toast.LENGTH_SHORT).show();
                }
                SystemNum = 10;
                break;

            case R.id.btn_hex:
                if(!tvInput.getText().toString().equals("")) {
                    try {
                        tvResult.setText(Calc(tvInput.getText().toString()));
                    }
                    catch (Exception e){
                        Toast.makeText(this,"算式错误" + e,Toast.LENGTH_SHORT).show();
                    }
                    tvInput.setText(null);
                }
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);
                btn5.setClickable(true);
                btn6.setClickable(true);
                btn7.setClickable(true);
                btn8.setClickable(true);
                btn9.setClickable(true);
                btnA.setClickable(true);
                btnB.setClickable(true);
                btnC.setClickable(true);
                btnD.setClickable(true);
                btnE.setClickable(true);
                btnF.setClickable(true);
                try {
                    //String text = Integer.toHexString(Integer.parseInt(tvResult.getText().toString(), SystemNum)).toUpperCase();
//                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
//                    String text = bi.toString(16).toUpperCase();
//                            Integer.toHexString(bi.intValue()).toUpperCase();
                    bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                    Long DecNum = bi.longValue();
//                    Toast.makeText(this,DecNum.toString(),Toast.LENGTH_LONG).show();
                    String text = Long.toHexString(DecNum).toUpperCase();
                    tvResult.setText(text);
                }
                catch (Exception e){
                    Toast.makeText(this,"无法转换进制" + e,Toast.LENGTH_SHORT).show();
                }
                SystemNum = 16;
                break;

            case R.id.btn_delete:
                String myStr=tvInput.getText().toString();
                try {
                    tvInput.setText(myStr.substring(0, myStr.length()-1));
                } catch (Exception e) {
                    tvInput.setText("");
                }
                break;

            case R.id.btn_CE:tvInput.setText(null);break;

            case R.id.btn_clear:
                tvInput.setText(null);
                tvResult.setText("0");
                break;

            case R.id.btn_0:
                InputNum("0");
                break;

            case R.id.btn_1:
                InputNum("1");
                break;

            case R.id.btn_2:
                InputNum("2");
                break;

            case R.id.btn_3:
                InputNum("3");
                break;

            case R.id.btn_4:
                InputNum("4");
                break;

            case R.id.btn_5:
                InputNum("5");
                break;

            case R.id.btn_6:
                InputNum("6");
                break;

            case R.id.btn_7:
                InputNum("7");
                break;

            case R.id.btn_8:
                InputNum("8");
                break;

            case R.id.btn_9:
                InputNum("9");
                break;

            case R.id.btn_A:
                InputNum("A");
                break;

            case R.id.btn_B:
                InputNum("B");
                break;

            case R.id.btn_C:
                InputNum("C");
                break;

            case R.id.btn_D:
                InputNum("D");
                break;

            case R.id.btn_E:
                InputNum("E");
                break;

            case R.id.btn_F:
                InputNum("F");
                break;

            case R.id.btn_plus:
                InputOperator("+");
                break;

            case R.id.btn_minus:
                InputOperator("-");
                break;

            case R.id.btn_multiply:
                InputOperator("*");
                break;

            case R.id.btn_divide:
                InputOperator("/");
                break;

            case R.id.btn_and:
                InputOperator(getString(R.string.text_and));
                break;

            case R.id.btn_in_or:
                InputOperator(getString(R.string.text_in_or));
                break;

            case R.id.btn_ex_or:
                InputOperator(getString(R.string.text_ex_or));
                break;

            case R.id.btn_unary:
                if(isClickEqu)
                {
                    tvInput.setText(null);
                    isClickEqu=false;
                }
                bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                Long un = ~ bi.longValue();
                String Unstr;
                switch (SystemNum) {
                    case 2:
                        //otStr = Integer.toBinaryString(otNum);
                        Unstr = Long.toBinaryString(un.longValue());
                        break;
                    case 8:
                        Unstr = Long.toOctalString(un.longValue());
                        break;
                    case 10:
                        Unstr = String.valueOf(un.longValue());
                        break;
                    case 16:
                        Unstr = Long.toHexString(un.longValue()).toUpperCase();
                        break;
                    default:
                        Unstr = "按位取反失败";
                        break;
                }
                tvResult.setText(Unstr);
                break;

            case R.id.btn_left_shift:
                InputOperator(getString(R.string.text_left_shift));
                break;

            case R.id.btn_right_shift:
                InputOperator(getString(R.string.text_right_shift));
                break;

            case R.id.btn_overthrow:
                if(isClickEqu)
                {
                    tvInput.setText(null);
                    isClickEqu=false;
                }
                bi = new BigInteger(tvResult.getText().toString(), SystemNum);
                bi = bi.negate();
                //BigInteger a = BigInteger.ZERO.subtract(bi);
                //Toast.makeText(this,String.valueOf(SystemNum),Toast.LENGTH_LONG).show();
                Long otNum;
                //otNum = Long.parseLong(tvResult.getText().toString(), SystemNum);
//                        bi.longValue();
                otNum = bi.longValue();

                String otStr;
                switch (SystemNum) {
                    case 2:
                        //otStr = Integer.toBinaryString(otNum);
                        otStr = Long.toBinaryString(otNum);
                        break;
                    case 8:
                        otStr = Long.toOctalString(otNum);
                        break;
                    case 10:
                        otStr = String.valueOf(otNum);
                        break;
                    case 16:
                        otStr = Long.toHexString(otNum).toUpperCase();
                        break;
                    default:
                        otStr = "相反数转换失败";
                        break;
                }
                tvResult.setText(otStr);
                break;

            case R.id.btn_equal:
                String myStringEqu=tvInput.getText().toString();
                if(myStringEqu.equals(null))
                {
                    return;
                }
                try {
                    tvResult.setText(Calc(myStringEqu));
                }
                catch (Exception e){
                    Toast.makeText(this,"算式错误" + e,Toast.LENGTH_SHORT).show();
                }
                isClickEqu=true;
                break;

            default: break;
        }
    }

    private void InputNum(String idx) {
        if(isClickEqu)
        {
            tvInput.setText(null);
            isClickEqu=false;
        }
        String myString=tvInput.getText().toString();
        myString+=idx;
        tvInput.setText(myString);
        hsv.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }

    private void InputOperator(String idx) {
        if(isClickEqu)
        {
            if(isNum(tvResult.getText().toString())) tvInput.setText(tvResult.getText().toString());
            else tvInput.setText(null);
            isClickEqu=false;
        }
        String myString=tvInput.getText().toString();
        myString+=idx;
        tvInput.setText(myString);
        hsv.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }

    private String Calc(String equation) {
        if(isNum(equation)) {
            bi = new BigInteger(tvInput.getText().toString(),SystemNum);
            String str;
            switch (SystemNum) {
                case 2:
                    //otStr = Integer.toBinaryString(otNum);
                    str = Long.toBinaryString(bi.longValue());
                    break;
                case 8:
                    str = Long.toOctalString(bi.longValue());
                    break;
                case 10:
                    str = String.valueOf(bi.longValue());
                    break;
                case 16:
                    str = Long.toHexString(bi.longValue()).toUpperCase();
                    break;
                default:
                    str = "算式错误";
                    break;
            }
            return str;
        }
        else {
            // 计算内容分割
            equation = equation.replace(getString(R.string.text_left_shift),getString(R.string.text_single_left_shift));
            equation = equation.replace(getString(R.string.text_right_shift),getString(R.string.text_single_right_shift));

            List<String> list0 = new ArrayList<>();
            int splitIndex = 0;
            for(int i=0;i<equation.length();i++){
                char c = equation.charAt(i);
                if(c == '+'||c == '-'||c == '*'||c == '/'||c == '&'||c == '|'||c == '^'||c == '<'||c == '>'){
                    BigInteger n = new BigInteger((equation.substring(splitIndex, i)),SystemNum);
                    list0.add(n.toString());
                    list0.add(c+"");
                    splitIndex = i+1;
                }
            }

            // 因为使用符号做判断，增加前一位和符号，所以最后一位数字不会在循环里处理
            BigInteger last = new BigInteger(equation.substring(splitIndex, equation.length()),SystemNum);
            list0.add(last.toString());

            // 乘除计算
            List<String> list1 = new ArrayList<>();
            Long temp = null; // 用于做乘除计算临时变量
            for(int i=1;i<list0.size();i+=2){ // 这里只循环运算符号
                if("+".equals(list0.get(i))||"-".equals(list0.get(i))||"&".equals(list0.get(i))||"|".equals(list0.get(i))||"^".equals(list0.get(i))||"<".equals(list0.get(i))||">".equals(list0.get(i))){
                    if(null != temp){ // 存在临时变量，说明前面进行过乘除计算
                        list1.add(temp.toString());
                        temp = null;
                    } else {
                        list1.add(list0.get(i - 1));
                    }
                    list1.add(list0.get(i)); // 把符号加进去
                    if(i==list0.size()-2) { // 处理到最后时遇到直接处理
                        list1.add(list0.get(i + 1));
                    }
                }else if("*".equals(list0.get(i))){
                    if(null == temp){
                        temp = Long.parseLong(list0.get(i - 1)) * Long.parseLong(list0.get(i + 1));
                    }else{
                        temp *= Long.parseLong(list0.get(i + 1));
                    }
                    if(i==list0.size()-2) { // 处理到最后时遇到直接处理
                        //tmp = new BigInteger(temp.toString(),SystemNum);
                        list1.add(temp.toString());
                        temp = null;
                    }
                }else if("/".equals(list0.get(i))){
                    if(null == temp){
                        temp = Long.parseLong(list0.get(i - 1)) / Long.parseLong(list0.get(i + 1));
                    }else{
                        temp /= Long.parseLong(list0.get(i + 1));
                    }
                    if(i==list0.size()-2) { // 处理到最后时遇到直接处理
                        //tmp = new BigInteger(temp.toString(),SystemNum);
                        list1.add(temp.toString());
                        temp = null;
                    }
                }
            }

            // 加减计算
            List<String> list2 = new ArrayList<>();
            temp = null; // 用于做乘除计算临时变量
            if(!list1.toString().contains("+") && !list1.toString().contains("-") && !list1.toString().contains("&") && !list1.toString().contains("|") && !list1.toString().contains("^") && !list1.toString().contains("<") && !list1.toString().contains(">")) list2 = list1;
            else {
                for(int i=1;i<list1.size();i+=2){ // 这里只循环运算符号
                    if("&".equals(list1.get(i))||"|".equals(list1.get(i))||"^".equals(list1.get(i))||"<".equals(list1.get(i))||">".equals(list1.get(i))){
                        if(null != temp){ // 存在临时变量，说明前面进行过乘除计算
                            list2.add(temp.toString());
                            temp = null;
                        } else {
                            list2.add(list1.get(i - 1));
                        }
                        list2.add(list1.get(i)); // 把符号加进去
                        if(i==list1.size()-2) { // 处理到最后时遇到直接处理
                            list2.add(list1.get(i + 1));
                        }
                    }else if("+".equals(list1.get(i))){
                        if(null == temp){
                            temp = Long.parseLong(list1.get(i - 1)) + Long.parseLong(list1.get(i + 1));
                        }else{
                            temp += Long.parseLong(list1.get(i + 1));
                        }
                        if(i==list1.size()-2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list2.add(temp.toString());
                            temp = null;
                        }
                    }else if("-".equals(list1.get(i))){
                        if(null == temp){
                            temp = Long.parseLong(list1.get(i - 1)) - Long.parseLong(list1.get(i + 1));
                        }else{
                            temp -= Long.parseLong(list1.get(i + 1));
                        }
                        if(i==list1.size()-2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list2.add(temp.toString());
                            temp = null;
                        }
                    }
                }
            }

            //左右移
            List<String> list3 = new ArrayList<>();
            temp = null; // 用于做乘除计算临时变量
            if(!list2.toString().contains("&") && !list2.toString().contains("|") && !list2.toString().contains("^") && !list2.toString().contains("<") && !list2.toString().contains(">")) list3 = list2;
            else {
                for(int i=1;i<list2.size();i+=2){ // 这里只循环运算符号
                    if("&".equals(list2.get(i))||"|".equals(list2.get(i))||"^".equals(list2.get(i))){
                        if(null != temp){ // 存在临时变量，说明前面进行过乘除计算
                            list3.add(temp.toString());
                            temp = null;
                        } else {
                            list3.add(list2.get(i - 1));
                        }
                        list3.add(list2.get(i)); // 把符号加进去
                        if(i==list2.size()-2) { // 处理到最后时遇到直接处理
                            list3.add(list2.get(i + 1));
                        }
                    }else if("<".equals(list2.get(i))){
                        if(null == temp){
                            temp = Long.parseLong(list2.get(i - 1)) << Long.parseLong(list2.get(i + 1));
                        }else{
                            temp <<= Long.parseLong(list2.get(i + 1));
                        }
                        if(i==list2.size()-2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list3.add(temp.toString());
                            temp = null;
                        }
                    }else if(">".equals(list2.get(i))) {
                        if (null == temp) {
                            temp = Long.parseLong(list2.get(i - 1)) >> Long.parseLong(list2.get(i + 1));
                        } else {
                            temp >>= Long.parseLong(list2.get(i + 1));
                        }
                        if (i == list2.size() - 2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list3.add(temp.toString());
                            temp = null;
                        }
                    }
                }
            }

            //按位与
            List<String> list4 = new ArrayList<>();
            temp = null; // 用于做乘除计算临时变量
            if(!list3.toString().contains("&") && !list3.toString().contains("|") && !list3.toString().contains("^")) list4 = list3;
            else {
                for(int i=1;i<list3.size();i+=2) { // 这里只循环运算符号
                    if ("|".equals(list3.get(i)) || "^".equals(list3.get(i))) {
                        if (null != temp) { // 存在临时变量，说明前面进行过乘除计算
                            list4.add(temp.toString());
                            temp = null;
                        } else {
                            list4.add(list3.get(i - 1));
                        }
                        list4.add(list3.get(i)); // 把符号加进去
                        if (i == list3.size() - 2) { // 处理到最后时遇到直接处理
                            list4.add(list3.get(i + 1));
                        }
                    } else if ("&".equals(list3.get(i))) {
                        if (null == temp) {
                            temp = Long.parseLong(list3.get(i - 1)) & Long.parseLong(list3.get(i + 1));
                        } else {
                            temp &= Long.parseLong(list3.get(i + 1));
                        }
                        if (i == list3.size() - 2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list4.add(temp.toString());
                            temp = null;
                        }
                    }
                }
            }

            //按位异或
            List<String> list5 = new ArrayList<>();
            temp = null; // 用于做乘除计算临时变量
            if(!list4.toString().contains("|") && !list4.toString().contains("^")) list5 = list4;
            else {
                for(int i=1;i<list4.size();i+=2) { // 这里只循环运算符号
                    if ("|".equals(list4.get(i))) {
                        if (null != temp) { // 存在临时变量，说明前面进行过乘除计算
                            list5.add(temp.toString());
                            temp = null;
                        } else {
                            list5.add(list4.get(i - 1));
                        }
                        list5.add(list4.get(i)); // 把符号加进去
                        if (i == list4.size() - 2) { // 处理到最后时遇到直接处理
                            list5.add(list4.get(i + 1));
                        }
                    } else if ("^".equals(list4.get(i))) {
                        if (null == temp) {
                            temp = Long.parseLong(list4.get(i - 1)) ^ Long.parseLong(list4.get(i + 1));
                        } else {
                            temp ^= Long.parseLong(list4.get(i + 1));
                        }
                        if (i == list4.size() - 2) { // 处理到最后时遇到直接处理
                            //tmp = new BigInteger(temp.toString(),SystemNum);
                            list5.add(temp.toString());
                            temp = null;
                        }
                    }
                }
            }

            //按位或
            Long sum;
            if(!list5.toString().contains("|")) {
                sum = Long.parseLong(list5.toString().replaceAll("[\\[|\\]]", ""));
            }
            else {
                sum = Long.parseLong(list5.get(0)); // 第一位不会在循环里处理
                for(int i=1;i<list5.size();i+=2){ // 这里只循环运算符号
                    if("|".equals(list5.get(i))){
                        sum |= Long.parseLong(list5.get(i + 1));
                    }
                }
            }

            return Change(sum);
        }
    }

    public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?|(([0-9[A-F]]+))?)$");
    }

    public String Change(Long num) {
        String str;
        switch (SystemNum) {
            case 2:
                //otStr = Integer.toBinaryString(otNum);
                str = Long.toBinaryString(num);
                break;
            case 8:
                str = Long.toOctalString(num);
                break;
            case 10:
                str = String.valueOf(num);
                break;
            case 16:
                str = Long.toHexString(num).toUpperCase();
                break;
            default:
                str = "进制错误";
                break;
        }
        return str;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about: {
                // 取得自定义View
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View about = layoutInflater.inflate(R.layout.activity_about, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("关于|About");
                builder.setIcon(R.mipmap.ic_launcher);
                //builder.setMessage(getString(R.string.text_version) + "\n" + getString(R.string.text_editor) + "\n" + getString(R.string.text_weibo) + "\n" + getString(R.string.text_homepage));
                builder.setView(about);
                builder.setPositiveButton
                        ("反馈", new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Feedback();
                                    }
                                }
                        );
                builder.setNegativeButton
                        ("返回", new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                }
                        );
                builder.show();
            }
            case R.id.action_setting: {
                if(!item.isChecked()) {
                    item.setChecked(true);
                    Vibratable = true;
                }
                else {
                    item.setChecked(false);
                    Vibratable = false;
                }
            }
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Feedback(){
        if(isConnected()){
            // 取得自定义View
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View feedback = layoutInflater.inflate(R.layout.feedback, null);

            final EditText etName = (EditText)feedback.findViewById(R.id.name);
            final EditText etEmail = (EditText)feedback.findViewById(R.id.email);
            final EditText etQQ = (EditText)feedback.findViewById(R.id.qq);
            final EditText etContent = (EditText)feedback.findViewById(R.id.content);

            new AlertDialog.Builder(this)
                    .setTitle("意见/建议")
                    .setIcon(R.mipmap.ic_launcher)
                    .setView(feedback)
                    .setPositiveButton("发送", new OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            final String yourname = etName.getText().toString().replaceAll("[\n|\r| ]", "");
                            final String email = etEmail.getText().toString().replaceAll("[\n|\r| ]", "");
                            final String qq = etQQ.getText().toString().replaceAll("[\n|\r| ]", "");
                            final String message = etContent.getText().toString().replaceAll("[\n|\r| ]", "");
//                            if(etContent.getText().toString().replaceAll("[\n|\r| ]", "") == ""){
//                                Toast.makeText(getApplication(), "内容不能为空", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
                            if(yourname == ""){
                                Toast.makeText(getApplication(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if(email == ""){
                                Toast.makeText(getApplication(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if(qq == ""){
                                Toast.makeText(getApplication(), "QQ号码不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if(message == ""){
                                Toast.makeText(getApplication(), "内容不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            showProgressDialog("正在发送意见/建议","请稍等…");
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    try {
                                        URL url = new URL(FeedbackURL + "?app_name="+ URLEncoder.encode(getString(R.string.app_name).replace("\n", "<br />").replace("+", "&#43;"))
                                                + "&yourname="+ URLEncoder.encode(yourname.replace("\n", "<br />").replace("+", "&#43;"))
                                                + "&email=" + URLEncoder.encode(email.replace("\n", "<br />").replace("+", "&#43;"))
                                                + "&qq=" + URLEncoder.encode(qq.replace("\n", "<br />").replace("+", "&#43;"))
                                                + "&message=" + URLEncoder.encode(message.replace("\n", "<br />").replace("+", "&#43;"))
                                                +"&add=1");
                                        //URLConnection conn = url.openConnection();
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        conn.setConnectTimeout(10000);
                                        conn.setReadTimeout(10000);
                                        conn.getInputStream();
                                        conn.disconnect();
                                        handle.sendEmptyMessage(0);
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        handle.sendEmptyMessage(-1);
                                        e.printStackTrace();
                                    }
                                }

                            }).start();
                        }

                    })
                    .setNegativeButton("取消", null).show();
            //dialog.getWindow().setAttributes(dialog.getWindow().getAttributes());
        } else Toast.makeText(getApplication(), "请检查网络连接", Toast.LENGTH_SHORT).show();
    }

    //判断是否连接上网络
    private boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)||(cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED);
    }

    //显示等待框（更新用）
    public void showProgressDialog(String title, String message){
        updateDialog = new ProgressDialog(MainActivity.this);
        updateDialog.setTitle(title);
        updateDialog.setMessage(message);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.show();
    }

    //更新要用到的东西
    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if(!updateDialog.isShowing())return;
            updateDialog.cancel();
            switch(msg.what){
                case -1:
                    Toast.makeText(getApplication(), "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(getApplication(), "发送成功，感谢您的意见和建议", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    String myStr=tvInput.getText().toString();
                    try {
                        tvInput.setText(myStr.substring(0, myStr.length()-1));
                    } catch (Exception e) {
                        tvInput.setText("");
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    };

    final Handler had = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(Vibratable) vibrator.vibrate(100);
                    String myStr=tvInput.getText().toString();
                    try {
                        tvInput.setText(myStr.substring(0, myStr.length()-1));
                    } catch (Exception e) {
                        tvInput.setText("");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };



    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
