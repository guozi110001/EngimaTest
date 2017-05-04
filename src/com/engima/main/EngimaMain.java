package com.engima.main;

import java.util.Scanner;

public class EngimaMain {
	
//	private static final String[] engBefore = {"a","b","c","d","e","f","g"};
	private static final String[] engBefore = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};
//	private static final String[] engAfter1 = {"a","b","c","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","X","Y","Z"," "};
//	private static final String[] engAfter2 = {"r","s","t","u","v","w","x","y","z","A","B","h","i","j","k","l","m","n","o","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," ","a","b","c","d","e","f","g","p","q"};
//	private static final String[] engAfter3 = {"z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"};
//	private static final String[] reflecter = {"z","U","V","W","X","Y","Z"," ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","t","u","v","w","x","y"};
	private static final int[] zz1 = {-10,-9,8,17,13,-7,-10,9,3,-16,-4,13,2,18,14,15,-17,19,-17,-7,6,11,16,11,1,-3,-19,17,20,8,17,-8,-13,9,-19,4,-7,16,20,16,-5,-20,20,-2,17,11,-19,-7,18,-16,7,-1,19};
	private static final int[] zz2 = {-19,28,-10,-23,-22,-22,3,-22,5,21,5,-17,-24,-11,-28,-7,26,1,-4,-3,-14,-9,29,20,24,-1,-23,27,-29,29,-2,-6,12,-11,6,-24,-13,-30,-11,-20,-8,29,-22,-6,-13,12,-25,16,2,4,-4,-25,-3};
	private static final int[] zz3 = {30,-28,5,1,-21,-16,3,-28,11,-27,-18,23,29,5,-17,-16,11,-26,-22,6,23,-4,-20,25,-12,6,-20,-14,19,10,21,-23,-17,21,-18,21,4,-27,-24,-2,6,-19,-22,10,-23,13,-18,-14,-25,-20,14,-27,-14};
	private static final int[] ref = {52,50,48,46,44,42,40,38,36,34,32,30,28,26,24,22,20,18,16,14,12,10,8,6,4,2,0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32,-34,-36,-38,-40,-42,-44,-46,-48,-50,-52};
//	private static final int[] zz1 = {4,-5,3,3,-3,2,-4};
//	private static final int[] zz2 = {5,2,-5,-4,-4,4,-5};
//	private static final int[] zz3 = {-2,-4,-1,4,2,4,-3};
//	private static final int[] ref = {6,4,2,0,-2,-4,-6};
	
	public static void main(String[] args) {
//		getZZS();
//		getReflecter();
//		for(int i=0;i<127;i++){
//			System.out.print(",\""+(char)i+"\"");
//		}
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("开始加密/解密，请输入转子");
			System.out.println("请输入转子1：");
			int z1 = sc.nextInt();
			System.out.println("请输入转子2：");
			int z2 = sc.nextInt();
			System.out.println("请输入转子3：");
			int z3 = sc.nextInt();
			sc.nextLine();
			System.out.println("请输入明文或密文：");
			String p = sc.nextLine();
			System.out.println("您输入的明文/密文是："+p);
			String engP = eng(p,z1,z2,z3);
			System.out.println("经过秘钥的加密/解密后是："+engP);
		}
//		
	}
	
	
	public static void getReflecter(){
		int[] reflect = new int[engBefore.length];
		for(int i=0;i<reflect.length;i++){
			reflect[i] = engBefore.length-i*2-1;
			System.out.print(","+reflect[i]);
		}
		
	}
	
	public static void getZZS(){
		int[] zzs = new int[engBefore.length];
		for(int i=0;i<engBefore.length;i++){
			int x = (int) ((int) (Math.random()*(engBefore.length/2)+1)*Math.pow(-1, (int)(Math.random()*2+1)));
			//System.out.println(x);
			int y = getCurrectIndex(i+x,ref);
			boolean flag = false;
			for(int k=0;k<zzs.length;k++){
				if(zzs[k]!=0&&getCurrectIndex(zzs[k]+k,ref)==y){
					flag = true;
					break;
				}
			}
			if(flag){
				i--;
				continue;
			}else{
				zzs[i] = x;
//				System.out.print(","+x);
			}
		}
//		System.out.println("jiehsu");
		for(int j=0;j<zzs.length;j++){
			System.out.print(","+zzs[j]);
		}
	}
	
	
	public static String eng(String p,int z1,int z2,int z3){
		String result = "";
		for(int i=0;i<p.length();i++){
			int a = getIndex(p.charAt(i)+"",engBefore);
			a = engSingle(a,null,zz1,0,z1);
			a = engSingle(a,zz1,zz2,z1,z2);
			a = engSingle(a,zz2,zz3,z2,z3);
			a = engReflect(a,z3);
			a = unEngSingle(a,null,zz3,0,z3);
			a = unEngSingle(a,zz3,zz2,z3,z2);
			a = unEngSingle(a,zz2,zz1,z2,z1);
			a = getCurrectIndex(a, zz1);
//			a = unEngSingle(a,engAfter1,engBefore,z3+z2+z1);
			result += engBefore[getCurrectIndex(a-z1,zz1)];
			z1++;
			if(z1>=zz1.length){
				z1 = 0;
				z2++;
				if(z2>=zz2.length){
					z2=0;
					z3++;
					if(z3>=zz3.length){
						z3 = 0;
					}
				}
			}
		}
		return result;
	}
	
	public static int engSingle(int p,int[] before,int[] after,int z1,int z2){
//		int x = p+z;
//		x = getCurrectIndex(x, before);
//		return before[x];
		int x = 0;
		if(before==null){
			x = p + (z2-z1);
		}else{
			x = p + before[p] - z1 + z2;
		}
		x = getCurrectIndex(x, after);
		return x;
	}
	
	public static int engReflect(int p,int z){
		p = getCurrectIndex(p, zz1);
		p = p + zz3[p]-z;
		p = p = getCurrectIndex(p, zz1);
		return p+ref[p];
	}
	
	public static int unEngSingle(int p,int[] before,int[] after,int z1,int z2){
//		int x = p-z;
//		x = getCurrectIndex(x, before);
//		return before[x];
		for(int i=0;i<after.length;i++){
//			int x = getCurrectIndex(i-z2,after);
			if(before==null){
				if(getCurrectIndex(i+after[i]-z2,after)==p)
					return i;
			}else{
				if(getCurrectIndex(i+after[i]-z2,after)==getCurrectIndex(p-z1,before))
					return i;
			}
		}
		return -1;
	}
	
	public static int getIndex(String p,String[] ps){
		for(int i=0;i<ps.length;i++){
			if(p.equals(ps[i])){
				return i;
			}
		}
		return -1;
	}
	
	public static int getCurrectIndex(int x,int[] zz){
		if(x>=zz.length)
			x = x%zz.length;
		while(x<0)
			x = zz.length+x;
		return x;
	}

}
