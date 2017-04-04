package hackerrank

/**
 * https://www.hackerrank.com/challenges/determining-dna-health
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 24, 2017
 */

class Gene{
	String name
	int weight

	public Gene(String name, int weight) {
		super();
		this.name = name
		this.weight = weight
	}

	public String toString(){
		return "${name}*${weight}"
	}
}

class GeneSerial{
	Gene[] genes

	GeneSerial(String[] geneNames, String[] weights){
		genes = new Gene[geneNames.length]
		for(int i =0 ; i<geneNames.length;i++){
			genes[i] = new Gene(geneNames[i], (weights[i] as int))
		}
	}

	int evaluate(String strand, int start, int end, int total = 0){
		if(start>genes.length-1 || end<1||start>end || null==strand || strand.trim() == ''){
			return 0
		}
		for(int i = start;i<end+1;i++){
			Gene g = genes[i]
			String checkStrand = strand
			int occ = checkStrand.indexOf(g.name)
			while(occ>-1&&checkStrand.length()>occ){
				total += g.weight
				checkStrand = checkStrand.substring(occ+1)
				occ = checkStrand.indexOf(g.name)
			}
		}
		return total
	}
}



//Scanner input = new Scanner(System.in)
//GeneSerial geneSerial
//TreeMap<Integer, Object> result = new TreeMap()
//int lineNum = 0
//while(input.hasNextLine()){
//	String ln = input.nextLine()
//	if(!ln){
//		break
//	}
//	if(lineNum == 1){
//		String[] genes = ln.split(' ')
//		String[] weight = input.nextLine().split(' ')
//		geneSerial = new GeneSerial(genes, weight)
//		lineNum++
//	}
//	if(lineNum >=4 ){
//		String[] check = ln.split(' ')
//		result.put(geneSerial.evaluate(check[2], check[0] as int, check[1] as int), null)
//	}
//	lineNum++
//}
//println "${result.keySet().getAt(0)} ${result.keySet().getAt(result.size()-1)}"



String inputData = '''100
ogwwsenipa obsehkjfcj dhqvptquuu kkdgivnvfc ytdqxmciue rznhvdcnxw kihnqpkdnp hlimdfbfnv mguznrcpfc nrmweeookb drolersfwh ckpykeqotx sioefulviv wnmkasbuzz ddkscwwukr rfzhjgbwbl rzagjaymua mxdyrhunbg eulfdxogtr rnrrtctrpp tdmzbfgxsi fyereiquol liyebrhvly kgbzfeembz wgxazdirzx flfdrgxydi woqzpwdvkg ugpuiqxrix qnkxsbfpcj zazqaqmdly mgebaorzfz yxoiuhmayo lyqkoqacwn aivgjxucxc cxzsgwbuya klyavotxsp muzickfwmc aqccjiakey mojmqgajfu yrozzqjfpw jrmltxvtkz twpejgmlpr gqlwpknbre xdvlqplmkv ngtfmelzsc qyudukojnh nkmjxdairm fgublhhygz byxvcuhsdu btgocgreqk syqnzeuicc ifahdebmwh jaapoexhio rcmjpnnlxq nfvonauqnt xwtznjdlqn bjqnshcgtz yghvwuwrml kmhdlumrhe einwxhebpx bnfilcejts ufebiqxwjh cnprmnysoq rrfwbqahzv atagwkwwif dkvsbjhcby surxqvqter oenpljzjhi rkuofwxoaa osugrmdjfh bwoolbzmkh wdtrrypqpp qdjmlcbomi wpekdpleex nabhtuhinw zfcksnntcb dyqiktzxzd ungxuzubkh almcwgrlbt mftcndxoaw sxjawdzshl zjxonvwegy ysfruuxtiz payzavecpn ppwofjjbop bojghfaeyj golgpodtst hhifwprhqf xuvgacodjm orcbxrpbnj uwtebrtsyl zxfugizuli gzzjawcszp btnwxrnqlm enljjrrile ssdtdgsfar xdlmaidpbp dhepqngkws oomuipccwc ttfeihplxs
4462805 1916916 2870812 3407597 5169525 4087301 4005965 1803633 3357388 1112112 5656776 4438527 3841975 4102090 2113339 2977711 1709727 1666821 4167887 3742911 1948785 3057238 1940358 4574138 4598641 2922682 1839758 4562812 1508583 2531144 3192788 4971388 3448060 2579952 4895338 5133938 5667253 5417655 3453923 3024642 5529768 3110699 3979521 5888095 3729142 2609212 2865806 1955221 3276034 3550045 4698132 1741171 5607283 5638490 2831662 1722277 2561172 3671420 5285089 5586108 5202564 4994229 2073848 5166977 1574181 5969186 1817267 1241435 2903194 1787542 5782429 4949314 1414593 1278302 4837409 4143735 2887514 4219567 5098956 2679900 1769612 1313440 5937424 3893247 3468283 2769086 2131876 5029455 2956858 3933318 2131915 4675774 2927547 3205764 1359103 1018081 5691302 2176370 3775868 2594496
100
7 93 zjxonvwegyeulfdxogtr
0 77 dkvsbjhcbyrzagjaymua
13 90 ddkscwwukrrfzhjgbwbl
9 85 xdvlqplmkvwgxazdirzx
7 77 jaapoexhiofgublhhygz
10 77 yghvwuwrmlbjqnshcgtz
19 91 ifahdebmwhxdvlqplmkv
6 84 jrmltxvtkzwgxazdirzx
12 85 oenpljzjhirkuofwxoaa
11 90 atagwkwwifmxdyrhunbg
12 70 dkvsbjhcbyfyereiquol
3 87 drolersfwhxwtznjdlqn
6 80 wnmkasbuzzrcmjpnnlxq
14 75 qnkxsbfpcjyxoiuhmayo
8 77 aivgjxucxczfcksnntcb
16 89 aivgjxucxcppwofjjbop
8 71 bjqnshcgtzugpuiqxrix
8 90 ppwofjjbopalmcwgrlbt
2 80 woqzpwdvkggqlwpknbre
4 91 kihnqpkdnpddkscwwukr
0 92 yrozzqjfpwdyqiktzxzd
14 77 xwtznjdlqneinwxhebpx
8 80 aivgjxucxcckpykeqotx
19 81 zjxonvwegyliyebrhvly
0 81 flfdrgxydinrmweeookb
8 87 xwtznjdlqngolgpodtst
2 89 ugpuiqxrixwpekdpleex
14 75 rnrrtctrppcxzsgwbuya
9 69 ddkscwwukrkmhdlumrhe
0 86 mftcndxoawnkmjxdairm
11 75 liyebrhvlyaivgjxucxc
3 83 einwxhebpxzjxonvwegy
18 73 qdjmlcbomidkvsbjhcby
9 78 nkmjxdairmwdtrrypqpp
10 92 rfzhjgbwblbojghfaeyj
16 85 syqnzeuiccqyudukojnh
3 85 yxoiuhmayomuzickfwmc
11 85 byxvcuhsdutwpejgmlpr
2 75 bnfilcejtsaqccjiakey
18 90 kgbzfeembzlyqkoqacwn
10 75 woqzpwdvkgrrfwbqahzv
10 82 ugpuiqxrixkmhdlumrhe
13 72 jaapoexhiorzagjaymua
4 76 jrmltxvtkzjaapoexhio
15 73 wgxazdirzxqnkxsbfpcj
2 93 woqzpwdvkgxwtznjdlqn
2 81 wpekdpleexddkscwwukr
14 86 fgublhhygzdyqiktzxzd
0 72 klyavotxsprznhvdcnxw
8 87 muzickfwmcbtgocgreqk
13 85 bnfilcejtsjaapoexhio
8 81 fyereiquoljrmltxvtkz
11 71 woqzpwdvkgwoqzpwdvkg
18 90 xdvlqplmkvwpekdpleex
11 93 zazqaqmdlypayzavecpn
3 75 ckpykeqotxtwpejgmlpr
6 89 dkvsbjhcbynabhtuhinw
18 70 liyebrhvlykmhdlumrhe
17 87 klyavotxspfyereiquol
7 87 yrozzqjfpwsioefulviv
15 79 yghvwuwrmlungxuzubkh
17 71 lyqkoqacwnyghvwuwrml
4 76 rkuofwxoaakmhdlumrhe
0 92 ifahdebmwhatagwkwwif
8 77 kgbzfeembzmxdyrhunbg
6 92 byxvcuhsduwnmkasbuzz
9 74 wdtrrypqppnfvonauqnt
18 89 nfvonauqntpayzavecpn
11 88 yxoiuhmayojaapoexhio
4 75 ddkscwwukrosugrmdjfh
18 92 wgxazdirzxmgebaorzfz
11 72 yxoiuhmayobyxvcuhsdu
3 86 syqnzeuiccdrolersfwh
5 93 muzickfwmcnfvonauqnt
0 76 hlimdfbfnvwgxazdirzx
13 86 aqccjiakeybjqnshcgtz
19 90 nkmjxdairmxuvgacodjm
2 76 syqnzeuicccnprmnysoq
17 72 kgbzfeembzjrmltxvtkz
9 73 eulfdxogtrliyebrhvly
3 69 oenpljzjhibyxvcuhsdu
19 76 ugpuiqxrixzazqaqmdly
15 86 atagwkwwifbyxvcuhsdu
5 82 nrmweeookbrkuofwxoaa
4 74 qdjmlcbomikihnqpkdnp
4 82 surxqvqterfgublhhygz
8 73 nkmjxdairmliyebrhvly
5 72 ckpykeqotxlyqkoqacwn
13 72 qdjmlcbomirfzhjgbwbl
2 73 wpekdpleexliyebrhvly
12 90 nkmjxdairmngtfmelzsc
4 72 twpejgmlpreulfdxogtr
5 93 rnrrtctrppzazqaqmdly
14 83 syqnzeuiccatagwkwwif
3 74 qdjmlcbomisioefulviv
18 85 qnkxsbfpcjdkvsbjhcby
14 86 xwtznjdlqneulfdxogtr
1 93 ifahdebmwhqnkxsbfpcj
5 79 ckpykeqotxgqlwpknbre
1 79 liyebrhvlymojmqgajfu'''
String[] lines = inputData.split('\n')
GeneSerial gs = new GeneSerial(lines[1].split(' '), lines[2].split(' '))

TreeMap<Integer, Object> res = new TreeMap()
(4..lines.length-1).each{
	String[] check = lines[it].split(' ')
	res.put(gs.evaluate(check[2], check[0] as int, check[1] as int), null)
}
println "${res.keySet().getAt(0)} ${res.keySet().getAt(res.size()-1)}"