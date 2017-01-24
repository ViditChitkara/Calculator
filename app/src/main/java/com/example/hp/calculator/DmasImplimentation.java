package com.example.hp.calculator;

/**
 * Created by HP on 28-Oct-16.
 */
import android.widget.TextView;

import java.util.HashMap;
class DmasNode {
    String value;
    DmasNode left;
    DmasNode right;
    public DmasNode(String value) {
        this.value=value;
        left=null;
        right=null;
    }
}
public class DmasImplimentation {
    public float evaluateExpressionWithBraces(String s){
        if(s.contains("C") && !s.contains("(")){
            int index=s.indexOf("C");
           return combination(s.substring(0,index),s.substring(index+1));
        }
        int i=0;
        String str="";
        while(i<s.length()){
            if(s.charAt(i)=='('){
                String temp="";
                i++;
                while(s.charAt(i)!=')'){
                    temp+=s.charAt(i);
                    i++;
                }
                if(temp.contains("C")){
                    int index=temp.indexOf("C");
                    str+=combination(temp.substring(0,index),temp.substring(index+1))+"";
                }
                else
                str+=evaluateExpression(temp)+"";
                i++;
            }
            else {
                str+=s.charAt(i);
                i++;
            }
        }
        return evaluateExpression(str);
    }
    private float combination(String a,String b){
        int n=(int)getVal(a),r=(int)getVal(b);
        int[][] storage=new int[n+1][n+1];
        combinationDP(n,r,storage);
        return storage[n][r];
    }
    private void combinationDP(int n,int r,int[][] storage){
        if(r==0||r==1||n==r){
            if(r==0||n==r){
                storage[n][r]=1;
                storage[n][n-r]=1;
            }
            else{
                storage[n][r]=n;
                storage[n][n-r]=n;}
            return;
        }
        int first=0,second=0;
        if(storage[n-1][r]!=0){
            first=storage[n-1][r];
        }
        else{
            combinationDP(n-1,r,storage);
            first=storage[n-1][r];
        }
        if(storage[n-1][r-1]!=0){
            second=storage[n-1][r-1];
        }
        else{
            combinationDP(n-1,r-1,storage);
            second=storage[n-1][r-1];
        }
        storage[n][r]=first+second;
    }
    private float evaluateExpression(String s){
        HashMap<String, Integer> pref=new HashMap<>();
        pref.put("+", 2);
        pref.put("*", 3);
        pref.put("/", 4);
        pref.put("^", 5);
        String str=makeEvaluable(s);
        DmasNode root=makeTree(str,pref);
        if(root.value.equals("")){
            return 0;
        }
        return getAns(root);

    }
    private String makeEvaluable(String s){
        String str=s.charAt(0)+"";
        int i=1;
        for(;i<s.length();i++){
            if(s.charAt(i-1)>=48&&s.charAt(i-1)<=57&&s.charAt(i)=='-'){
                str+="+-";
            }
            else{
                str+=s.charAt(i);
            }
        }
        return str;
    }
    private DmasNode makeTree(String str,HashMap<String,Integer> map){
        if(!str.contains("+") && !str.contains("*") && !str.contains("/") && !str.contains("^")){
            DmasNode temp=new DmasNode(str);
            return temp;
        }
        char leastPref='\0';
        int pos=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!='.'&&(str.charAt(i)<48 || str.charAt(i)>57)){
                if(str.charAt(i)!='-' && leastPref=='\0'){
                    leastPref=str.charAt(i);
                    pos=i;
                }
                else{
                    if(str.charAt(i)!='-' && map.get(str.charAt(i)+"")<map.get(leastPref+"")){
                        leastPref=str.charAt(i);
                        pos=i;
                    }
                }
            }
        }
        DmasNode node=new DmasNode(leastPref+"");
        node.left=makeTree(str.substring(0,pos), map);
        node.right=makeTree(str.substring(pos+1), map);
        return node;
    }
    private float getAns(DmasNode tree){
        if(tree.left==null && tree.right==null){
            float ans=getVal(tree.value);
            return ans;
        }
        if(!tree.left.value.contains("+")&& !tree.left.value.contains("*") && !tree.left.value.contains("/") && !tree.left.value.contains("^")){
            if(!tree.right.value.contains("+") && !tree.right.value.contains("*") && !tree.right.value.contains("/") && !tree.right.value.contains("^")){
                float first=getVal(tree.left.value),second=getVal(tree.right.value);
                if(tree.value.equals("+")){
                    return first+second;
                }
                else if(tree.value.equals("*")){
                    return first*second;
                }
                else if(tree.value.equals("/")){
                    return first/second;
                }
                else if(tree.value.equals("^")){
                    if(first<0){
                        first=first*-1;
                        return -1*power(first,second);
                    }
                    return power(first,second);
                }
            }
        }
        float first=getAns(tree.left);
        float second=getAns(tree.right);
        if(tree.value.equals("+")){
            return first+second;
        }
        else if(tree.value.equals("*")){
            return first*second;
        }
        else if(tree.value.equals("^")){
            if(first<0){
                first=first*-1;
                return -1*power(first*-1,second);
            }
            return power(first,second);
        }
        else{
            return first/second;
        }

    }
    private float getVal(String s){
        if(s.contains(".")){
            return getValDecimal(s);
        }
        else if(s.length()==1){
            return s.charAt(0)-'0';
        }
        else if(s.charAt(0)=='-'){
            return (getVal(s.substring(1))*-1);
        }
        else{
            if(s.contains("-")){
                int pos=s.indexOf('-');
                float first=getVal(s.substring(0,pos));
                float second=getVal(s.substring(pos+1));
                return (first-second);
            }
            float ans=(s.charAt(s.length()-1)-'0')+getVal(s.substring(0,(s.length()-1)))*10;
            return ans;
        }
    }
    private float getValDecimal(String s){
        int point=s.indexOf('.'),i=0;
        if(s.charAt(i)=='-'){
            return getVal(s.substring(1))*-1;
        }
        float before=0,after=0;
        for(i=0;i<point;i++){
            before+=(s.charAt(i)-'0')*(Math.pow(10,point-i-1));
        }
        for(i=point+1;i<s.length();i++){
            after+=(s.charAt(i)-'0')*(Math.pow(10,s.length()-1-i));
        }
        float ans=before+(float)(after/(Math.pow(10,s.length()-1-point)));
        return ans;

    }
    private static float power(float first,float second){
        return (float)Math.pow(first, second);
    }
}
