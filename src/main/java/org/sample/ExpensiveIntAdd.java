package org.sample;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ExpensiveIntAdd
{
    @Benchmark
    public int add1396()
    {
        int sum = 0;
        
        sum += 1; sum += 2; sum += 3; sum += 4; sum += 5; sum += 6; sum += 7; sum += 8; sum += 9; sum += 10; 
        sum += 11; sum += 12; sum += 13; sum += 14; sum += 15; sum += 16; sum += 17; sum += 18; sum += 19; sum += 20; 
        sum += 21; sum += 22; sum += 23; sum += 24; sum += 25; sum += 26; sum += 27; sum += 28; sum += 29; sum += 30; 
        sum += 31; sum += 32; sum += 33; sum += 34; sum += 35; sum += 36; sum += 37; sum += 38; sum += 39; sum += 40; 
        sum += 41; sum += 42; sum += 43; sum += 44; sum += 45; sum += 46; sum += 47; sum += 48; sum += 49; sum += 50; 
        sum += 51; sum += 52; sum += 53; sum += 54; sum += 55; sum += 56; sum += 57; sum += 58; sum += 59; sum += 60; 
        sum += 61; sum += 62; sum += 63; sum += 64; sum += 65; sum += 66; sum += 67; sum += 68; sum += 69; sum += 70; 
        sum += 71; sum += 72; sum += 73; sum += 74; sum += 75; sum += 76; sum += 77; sum += 78; sum += 79; sum += 80; 
        sum += 81; sum += 82; sum += 83; sum += 84; sum += 85; sum += 86; sum += 87; sum += 88; sum += 89; sum += 90; 
        sum += 91; sum += 92; sum += 93; sum += 94; sum += 95; sum += 96; sum += 97; sum += 98; sum += 99; sum += 100; 
        sum += 101; sum += 102; sum += 103; sum += 104; sum += 105; sum += 106; sum += 107; sum += 108; sum += 109; sum += 110; 
        sum += 111; sum += 112; sum += 113; sum += 114; sum += 115; sum += 116; sum += 117; sum += 118; sum += 119; sum += 120; 
        sum += 121; sum += 122; sum += 123; sum += 124; sum += 125; sum += 126; sum += 127; sum += 128; sum += 129; sum += 130; 
        sum += 131; sum += 132; sum += 133; sum += 134; sum += 135; sum += 136; sum += 137; sum += 138; sum += 139; sum += 140; 
        sum += 141; sum += 142; sum += 143; sum += 144; sum += 145; sum += 146; sum += 147; sum += 148; sum += 149; sum += 150; 
        sum += 151; sum += 152; sum += 153; sum += 154; sum += 155; sum += 156; sum += 157; sum += 158; sum += 159; sum += 160; 
        sum += 161; sum += 162; sum += 163; sum += 164; sum += 165; sum += 166; sum += 167; sum += 168; sum += 169; sum += 170; 
        sum += 171; sum += 172; sum += 173; sum += 174; sum += 175; sum += 176; sum += 177; sum += 178; sum += 179; sum += 180; 
        sum += 181; sum += 182; sum += 183; sum += 184; sum += 185; sum += 186; sum += 187; sum += 188; sum += 189; sum += 190; 
        sum += 191; sum += 192; sum += 193; sum += 194; sum += 195; sum += 196; sum += 197; sum += 198; sum += 199; sum += 200; 
        sum += 201; sum += 202; sum += 203; sum += 204; sum += 205; sum += 206; sum += 207; sum += 208; sum += 209; sum += 210; 
        sum += 211; sum += 212; sum += 213; sum += 214; sum += 215; sum += 216; sum += 217; sum += 218; sum += 219; sum += 220; 
        sum += 221; sum += 222; sum += 223; sum += 224; sum += 225; sum += 226; sum += 227; sum += 228; sum += 229; sum += 230; 
        sum += 231; sum += 232; sum += 233; sum += 234; sum += 235; sum += 236; sum += 237; sum += 238; sum += 239; sum += 240; 
        sum += 241; sum += 242; sum += 243; sum += 244; sum += 245; sum += 246; sum += 247; sum += 248; sum += 249; sum += 250; 
        sum += 251; sum += 252; sum += 253; sum += 254; sum += 255; sum += 256; sum += 257; sum += 258; sum += 259; sum += 260; 
        sum += 261; sum += 262; sum += 263; sum += 264; sum += 265; sum += 266; sum += 267; sum += 268; sum += 269; sum += 270; 
        sum += 271; sum += 272; sum += 273; sum += 274; sum += 275; sum += 276; sum += 277; sum += 278; sum += 279; sum += 280; 
        sum += 281; sum += 282; sum += 283; sum += 284; sum += 285; sum += 286; sum += 287; sum += 288; sum += 289; sum += 290; 
        sum += 291; sum += 292; sum += 293; sum += 294; sum += 295; sum += 296; sum += 297; sum += 298; sum += 299; sum += 300; 
        sum += 301; sum += 302; sum += 303; sum += 304; sum += 305; sum += 306; sum += 307; sum += 308; sum += 309; sum += 310; 
        sum += 311; sum += 312; sum += 313; sum += 314; sum += 315; sum += 316; sum += 317; sum += 318; sum += 319; sum += 320; 
        sum += 321; sum += 322; sum += 323; sum += 324; sum += 325; sum += 326; sum += 327; sum += 328; sum += 329; sum += 330; 
        sum += 331; sum += 332; sum += 333; sum += 334; sum += 335; sum += 336; sum += 337; sum += 338; sum += 339; sum += 340; 
        sum += 341; sum += 342; sum += 343; sum += 344; sum += 345; sum += 346; sum += 347; sum += 348; sum += 349; sum += 350; 
        sum += 351; sum += 352; sum += 353; sum += 354; sum += 355; sum += 356; sum += 357; sum += 358; sum += 359; sum += 360; 
        sum += 361; sum += 362; sum += 363; sum += 364; sum += 365; sum += 366; sum += 367; sum += 368; sum += 369; sum += 370; 
        sum += 371; sum += 372; sum += 373; sum += 374; sum += 375; sum += 376; sum += 377; sum += 378; sum += 379; sum += 380; 
        sum += 381; sum += 382; sum += 383; sum += 384; sum += 385; sum += 386; sum += 387; sum += 388; sum += 389; sum += 390; 
        sum += 391; sum += 392; sum += 393; sum += 394; sum += 395; sum += 396; sum += 397; sum += 398; sum += 399; sum += 400; 
        sum += 401; sum += 402; sum += 403; sum += 404; sum += 405; sum += 406; sum += 407; sum += 408; sum += 409; sum += 410; 
        sum += 411; sum += 412; sum += 413; sum += 414; sum += 415; sum += 416; sum += 417; sum += 418; sum += 419; sum += 420; 
        sum += 421; sum += 422; sum += 423; sum += 424; sum += 425; sum += 426; sum += 427; sum += 428; sum += 429; sum += 430; 
        sum += 431; sum += 432; sum += 433; sum += 434; sum += 435; sum += 436; sum += 437; sum += 438; sum += 439; sum += 440; 
        sum += 441; sum += 442; sum += 443; sum += 444; sum += 445; sum += 446; sum += 447; sum += 448; sum += 449; sum += 450; 
        sum += 451; sum += 452; sum += 453; sum += 454; sum += 455; sum += 456; sum += 457; sum += 458; sum += 459; sum += 460; 
        sum += 461; sum += 462; sum += 463; sum += 464; sum += 465; sum += 466; sum += 467; sum += 468; sum += 469; sum += 470; 
        sum += 471; sum += 472; sum += 473; sum += 474; sum += 475; sum += 476; sum += 477; sum += 478; sum += 479; sum += 480; 
        sum += 481; sum += 482; sum += 483; sum += 484; sum += 485; sum += 486; sum += 487; sum += 488; sum += 489; sum += 490; 
        sum += 491; sum += 492; sum += 493; sum += 494; sum += 495; sum += 496; sum += 497; sum += 498; sum += 499; sum += 500; 
        sum += 501; sum += 502; sum += 503; sum += 504; sum += 505; sum += 506; sum += 507; sum += 508; sum += 509; sum += 510; 
        sum += 511; sum += 512; sum += 513; sum += 514; sum += 515; sum += 516; sum += 517; sum += 518; sum += 519; sum += 520; 
        sum += 521; sum += 522; sum += 523; sum += 524; sum += 525; sum += 526; sum += 527; sum += 528; sum += 529; sum += 530; 
        sum += 531; sum += 532; sum += 533; sum += 534; sum += 535; sum += 536; sum += 537; sum += 538; sum += 539; sum += 540; 
        sum += 541; sum += 542; sum += 543; sum += 544; sum += 545; sum += 546; sum += 547; sum += 548; sum += 549; sum += 550; 
        sum += 551; sum += 552; sum += 553; sum += 554; sum += 555; sum += 556; sum += 557; sum += 558; sum += 559; sum += 560; 
        sum += 561; sum += 562; sum += 563; sum += 564; sum += 565; sum += 566; sum += 567; sum += 568; sum += 569; sum += 570; 
        sum += 571; sum += 572; sum += 573; sum += 574; sum += 575; sum += 576; sum += 577; sum += 578; sum += 579; sum += 580; 
        sum += 581; sum += 582; sum += 583; sum += 584; sum += 585; sum += 586; sum += 587; sum += 588; sum += 589; sum += 590; 
        sum += 591; sum += 592; sum += 593; sum += 594; sum += 595; sum += 596; sum += 597; sum += 598; sum += 599; sum += 600; 
        sum += 601; sum += 602; sum += 603; sum += 604; sum += 605; sum += 606; sum += 607; sum += 608; sum += 609; sum += 610; 
        sum += 611; sum += 612; sum += 613; sum += 614; sum += 615; sum += 616; sum += 617; sum += 618; sum += 619; sum += 620; 
        sum += 621; sum += 622; sum += 623; sum += 624; sum += 625; sum += 626; sum += 627; sum += 628; sum += 629; sum += 630; 
        sum += 631; sum += 632; sum += 633; sum += 634; sum += 635; sum += 636; sum += 637; sum += 638; sum += 639; sum += 640; 
        sum += 641; sum += 642; sum += 643; sum += 644; sum += 645; sum += 646; sum += 647; sum += 648; sum += 649; sum += 650; 
        sum += 651; sum += 652; sum += 653; sum += 654; sum += 655; sum += 656; sum += 657; sum += 658; sum += 659; sum += 660; 
        sum += 661; sum += 662; sum += 663; sum += 664; sum += 665; sum += 666; sum += 667; sum += 668; sum += 669; sum += 670; 
        sum += 671; sum += 672; sum += 673; sum += 674; sum += 675; sum += 676; sum += 677; sum += 678; sum += 679; sum += 680; 
        sum += 681; sum += 682; sum += 683; sum += 684; sum += 685; sum += 686; sum += 687; sum += 688; sum += 689; sum += 690; 
        sum += 691; sum += 692; sum += 693; sum += 694; sum += 695; sum += 696; sum += 697; sum += 698; sum += 699; sum += 700; 
        sum += 701; sum += 702; sum += 703; sum += 704; sum += 705; sum += 706; sum += 707; sum += 708; sum += 709; sum += 710; 
        sum += 711; sum += 712; sum += 713; sum += 714; sum += 715; sum += 716; sum += 717; sum += 718; sum += 719; sum += 720; 
        sum += 721; sum += 722; sum += 723; sum += 724; sum += 725; sum += 726; sum += 727; sum += 728; sum += 729; sum += 730; 
        sum += 731; sum += 732; sum += 733; sum += 734; sum += 735; sum += 736; sum += 737; sum += 738; sum += 739; sum += 740; 
        sum += 741; sum += 742; sum += 743; sum += 744; sum += 745; sum += 746; sum += 747; sum += 748; sum += 749; sum += 750; 
        sum += 751; sum += 752; sum += 753; sum += 754; sum += 755; sum += 756; sum += 757; sum += 758; sum += 759; sum += 760; 
        sum += 761; sum += 762; sum += 763; sum += 764; sum += 765; sum += 766; sum += 767; sum += 768; sum += 769; sum += 770; 
        sum += 771; sum += 772; sum += 773; sum += 774; sum += 775; sum += 776; sum += 777; sum += 778; sum += 779; sum += 780; 
        sum += 781; sum += 782; sum += 783; sum += 784; sum += 785; sum += 786; sum += 787; sum += 788; sum += 789; sum += 790; 
        sum += 791; sum += 792; sum += 793; sum += 794; sum += 795; sum += 796; sum += 797; sum += 798; sum += 799; sum += 800; 
        sum += 801; sum += 802; sum += 803; sum += 804; sum += 805; sum += 806; sum += 807; sum += 808; sum += 809; sum += 810; 
        sum += 811; sum += 812; sum += 813; sum += 814; sum += 815; sum += 816; sum += 817; sum += 818; sum += 819; sum += 820; 
        sum += 821; sum += 822; sum += 823; sum += 824; sum += 825; sum += 826; sum += 827; sum += 828; sum += 829; sum += 830; 
        sum += 831; sum += 832; sum += 833; sum += 834; sum += 835; sum += 836; sum += 837; sum += 838; sum += 839; sum += 840; 
        sum += 841; sum += 842; sum += 843; sum += 844; sum += 845; sum += 846; sum += 847; sum += 848; sum += 849; sum += 850; 
        sum += 851; sum += 852; sum += 853; sum += 854; sum += 855; sum += 856; sum += 857; sum += 858; sum += 859; sum += 860; 
        sum += 861; sum += 862; sum += 863; sum += 864; sum += 865; sum += 866; sum += 867; sum += 868; sum += 869; sum += 870; 
        sum += 871; sum += 872; sum += 873; sum += 874; sum += 875; sum += 876; sum += 877; sum += 878; sum += 879; sum += 880; 
        sum += 881; sum += 882; sum += 883; sum += 884; sum += 885; sum += 886; sum += 887; sum += 888; sum += 889; sum += 890; 
        sum += 891; sum += 892; sum += 893; sum += 894; sum += 895; sum += 896; sum += 897; sum += 898; sum += 899; sum += 900; 
        sum += 901; sum += 902; sum += 903; sum += 904; sum += 905; sum += 906; sum += 907; sum += 908; sum += 909; sum += 910; 
        sum += 911; sum += 912; sum += 913; sum += 914; sum += 915; sum += 916; sum += 917; sum += 918; sum += 919; sum += 920; 
        sum += 921; sum += 922; sum += 923; sum += 924; sum += 925; sum += 926; sum += 927; sum += 928; sum += 929; sum += 930; 
        sum += 931; sum += 932; sum += 933; sum += 934; sum += 935; sum += 936; sum += 937; sum += 938; sum += 939; sum += 940; 
        sum += 941; sum += 942; sum += 943; sum += 944; sum += 945; sum += 946; sum += 947; sum += 948; sum += 949; sum += 950; 
        sum += 951; sum += 952; sum += 953; sum += 954; sum += 955; sum += 956; sum += 957; sum += 958; sum += 959; sum += 960; 
        sum += 961; sum += 962; sum += 963; sum += 964; sum += 965; sum += 966; sum += 967; sum += 968; sum += 969; sum += 970; 
        sum += 971; sum += 972; sum += 973; sum += 974; sum += 975; sum += 976; sum += 977; sum += 978; sum += 979; sum += 980; 
        sum += 981; sum += 982; sum += 983; sum += 984; sum += 985; sum += 986; sum += 987; sum += 988; sum += 989; sum += 990; 
        sum += 991; sum += 992; sum += 993; sum += 994; sum += 995; sum += 996; sum += 997; sum += 998; sum += 999; sum += 1000; 
        sum += 1001; sum += 1002; sum += 1003; sum += 1004; sum += 1005; sum += 1006; sum += 1007; sum += 1008; sum += 1009; sum += 1010; 
        sum += 1011; sum += 1012; sum += 1013; sum += 1014; sum += 1015; sum += 1016; sum += 1017; sum += 1018; sum += 1019; sum += 1020; 
        sum += 1021; sum += 1022; sum += 1023; sum += 1024; sum += 1025; sum += 1026; sum += 1027; sum += 1028; sum += 1029; sum += 1030; 
        sum += 1031; sum += 1032; sum += 1033; sum += 1034; sum += 1035; sum += 1036; sum += 1037; sum += 1038; sum += 1039; sum += 1040; 
        sum += 1041; sum += 1042; sum += 1043; sum += 1044; sum += 1045; sum += 1046; sum += 1047; sum += 1048; sum += 1049; sum += 1050; 
        sum += 1051; sum += 1052; sum += 1053; sum += 1054; sum += 1055; sum += 1056; sum += 1057; sum += 1058; sum += 1059; sum += 1060; 
        sum += 1061; sum += 1062; sum += 1063; sum += 1064; sum += 1065; sum += 1066; sum += 1067; sum += 1068; sum += 1069; sum += 1070; 
        sum += 1071; sum += 1072; sum += 1073; sum += 1074; sum += 1075; sum += 1076; sum += 1077; sum += 1078; sum += 1079; sum += 1080; 
        sum += 1081; sum += 1082; sum += 1083; sum += 1084; sum += 1085; sum += 1086; sum += 1087; sum += 1088; sum += 1089; sum += 1090; 
        sum += 1091; sum += 1092; sum += 1093; sum += 1094; sum += 1095; sum += 1096; sum += 1097; sum += 1098; sum += 1099; sum += 1100; 
        sum += 1101; sum += 1102; sum += 1103; sum += 1104; sum += 1105; sum += 1106; sum += 1107; sum += 1108; sum += 1109; sum += 1110; 
        sum += 1111; sum += 1112; sum += 1113; sum += 1114; sum += 1115; sum += 1116; sum += 1117; sum += 1118; sum += 1119; sum += 1120; 
        sum += 1121; sum += 1122; sum += 1123; sum += 1124; sum += 1125; sum += 1126; sum += 1127; sum += 1128; sum += 1129; sum += 1130; 
        sum += 1131; sum += 1132; sum += 1133; sum += 1134; sum += 1135; sum += 1136; sum += 1137; sum += 1138; sum += 1139; sum += 1140; 
        sum += 1141; sum += 1142; sum += 1143; sum += 1144; sum += 1145; sum += 1146; sum += 1147; sum += 1148; sum += 1149; sum += 1150; 
        sum += 1151; sum += 1152; sum += 1153; sum += 1154; sum += 1155; sum += 1156; sum += 1157; sum += 1158; sum += 1159; sum += 1160; 
        sum += 1161; sum += 1162; sum += 1163; sum += 1164; sum += 1165; sum += 1166; sum += 1167; sum += 1168; sum += 1169; sum += 1170; 
        sum += 1171; sum += 1172; sum += 1173; sum += 1174; sum += 1175; sum += 1176; sum += 1177; sum += 1178; sum += 1179; sum += 1180; 
        sum += 1181; sum += 1182; sum += 1183; sum += 1184; sum += 1185; sum += 1186; sum += 1187; sum += 1188; sum += 1189; sum += 1190; 
        sum += 1191; sum += 1192; sum += 1193; sum += 1194; sum += 1195; sum += 1196; sum += 1197; sum += 1198; sum += 1199; sum += 1200; 
        sum += 1201; sum += 1202; sum += 1203; sum += 1204; sum += 1205; sum += 1206; sum += 1207; sum += 1208; sum += 1209; sum += 1210; 
        sum += 1211; sum += 1212; sum += 1213; sum += 1214; sum += 1215; sum += 1216; sum += 1217; sum += 1218; sum += 1219; sum += 1220; 
        sum += 1221; sum += 1222; sum += 1223; sum += 1224; sum += 1225; sum += 1226; sum += 1227; sum += 1228; sum += 1229; sum += 1230; 
        sum += 1231; sum += 1232; sum += 1233; sum += 1234; sum += 1235; sum += 1236; sum += 1237; sum += 1238; sum += 1239; sum += 1240; 
        sum += 1241; sum += 1242; sum += 1243; sum += 1244; sum += 1245; sum += 1246; sum += 1247; sum += 1248; sum += 1249; sum += 1250; 
        sum += 1251; sum += 1252; sum += 1253; sum += 1254; sum += 1255; sum += 1256; sum += 1257; sum += 1258; sum += 1259; sum += 1260; 
        sum += 1261; sum += 1262; sum += 1263; sum += 1264; sum += 1265; sum += 1266; sum += 1267; sum += 1268; sum += 1269; sum += 1270; 
        sum += 1271; sum += 1272; sum += 1273; sum += 1274; sum += 1275; sum += 1276; sum += 1277; sum += 1278; sum += 1279; sum += 1280; 
        sum += 1281; sum += 1282; sum += 1283; sum += 1284; sum += 1285; sum += 1286; sum += 1287; sum += 1288; sum += 1289; sum += 1290; 
        sum += 1291; sum += 1292; sum += 1293; sum += 1294; sum += 1295; sum += 1296; sum += 1297; sum += 1298; sum += 1299; sum += 1300; 
        sum += 1301; sum += 1302; sum += 1303; sum += 1304; sum += 1305; sum += 1306; sum += 1307; sum += 1308; sum += 1309; sum += 1310; 
        sum += 1311; sum += 1312; sum += 1313; sum += 1314; sum += 1315; sum += 1316; sum += 1317; sum += 1318; sum += 1319; sum += 1320; 
        sum += 1321; sum += 1322; sum += 1323; sum += 1324; sum += 1325; sum += 1326; sum += 1327; sum += 1328; sum += 1329; sum += 1330; 
        sum += 1331; sum += 1332; sum += 1333; sum += 1334; sum += 1335; sum += 1336; sum += 1337; sum += 1338; sum += 1339; sum += 1340; 
        sum += 1341; sum += 1342; sum += 1343; sum += 1344; sum += 1345; sum += 1346; sum += 1347; sum += 1348; sum += 1349; sum += 1350; 
        sum += 1351; sum += 1352; sum += 1353; sum += 1354; sum += 1355; sum += 1356; sum += 1357; sum += 1358; sum += 1359; sum += 1360; 
        sum += 1361; sum += 1362; sum += 1363; sum += 1364; sum += 1365; sum += 1366; sum += 1367; sum += 1368; sum += 1369; sum += 1370; 
        sum += 1371; sum += 1372; sum += 1373; sum += 1374; sum += 1375; sum += 1376; sum += 1377; sum += 1378; sum += 1379; sum += 1380; 
        sum += 1381; sum += 1382; sum += 1383; sum += 1384; sum += 1385; sum += 1386; sum += 1387; sum += 1388; sum += 1389; sum += 1390; 
        sum += 1391; sum += 1392; sum += 1393; sum += 1394; sum += 1395; sum += 1396;
        
        return sum;
    }

    @Benchmark
    public int add1397()
    {
        int sum = 0;
        
        sum += 1; sum += 2; sum += 3; sum += 4; sum += 5; sum += 6; sum += 7; sum += 8; sum += 9; sum += 10; 
        sum += 11; sum += 12; sum += 13; sum += 14; sum += 15; sum += 16; sum += 17; sum += 18; sum += 19; sum += 20; 
        sum += 21; sum += 22; sum += 23; sum += 24; sum += 25; sum += 26; sum += 27; sum += 28; sum += 29; sum += 30; 
        sum += 31; sum += 32; sum += 33; sum += 34; sum += 35; sum += 36; sum += 37; sum += 38; sum += 39; sum += 40; 
        sum += 41; sum += 42; sum += 43; sum += 44; sum += 45; sum += 46; sum += 47; sum += 48; sum += 49; sum += 50; 
        sum += 51; sum += 52; sum += 53; sum += 54; sum += 55; sum += 56; sum += 57; sum += 58; sum += 59; sum += 60; 
        sum += 61; sum += 62; sum += 63; sum += 64; sum += 65; sum += 66; sum += 67; sum += 68; sum += 69; sum += 70; 
        sum += 71; sum += 72; sum += 73; sum += 74; sum += 75; sum += 76; sum += 77; sum += 78; sum += 79; sum += 80; 
        sum += 81; sum += 82; sum += 83; sum += 84; sum += 85; sum += 86; sum += 87; sum += 88; sum += 89; sum += 90; 
        sum += 91; sum += 92; sum += 93; sum += 94; sum += 95; sum += 96; sum += 97; sum += 98; sum += 99; sum += 100; 
        sum += 101; sum += 102; sum += 103; sum += 104; sum += 105; sum += 106; sum += 107; sum += 108; sum += 109; sum += 110; 
        sum += 111; sum += 112; sum += 113; sum += 114; sum += 115; sum += 116; sum += 117; sum += 118; sum += 119; sum += 120; 
        sum += 121; sum += 122; sum += 123; sum += 124; sum += 125; sum += 126; sum += 127; sum += 128; sum += 129; sum += 130; 
        sum += 131; sum += 132; sum += 133; sum += 134; sum += 135; sum += 136; sum += 137; sum += 138; sum += 139; sum += 140; 
        sum += 141; sum += 142; sum += 143; sum += 144; sum += 145; sum += 146; sum += 147; sum += 148; sum += 149; sum += 150; 
        sum += 151; sum += 152; sum += 153; sum += 154; sum += 155; sum += 156; sum += 157; sum += 158; sum += 159; sum += 160; 
        sum += 161; sum += 162; sum += 163; sum += 164; sum += 165; sum += 166; sum += 167; sum += 168; sum += 169; sum += 170; 
        sum += 171; sum += 172; sum += 173; sum += 174; sum += 175; sum += 176; sum += 177; sum += 178; sum += 179; sum += 180; 
        sum += 181; sum += 182; sum += 183; sum += 184; sum += 185; sum += 186; sum += 187; sum += 188; sum += 189; sum += 190; 
        sum += 191; sum += 192; sum += 193; sum += 194; sum += 195; sum += 196; sum += 197; sum += 198; sum += 199; sum += 200; 
        sum += 201; sum += 202; sum += 203; sum += 204; sum += 205; sum += 206; sum += 207; sum += 208; sum += 209; sum += 210; 
        sum += 211; sum += 212; sum += 213; sum += 214; sum += 215; sum += 216; sum += 217; sum += 218; sum += 219; sum += 220; 
        sum += 221; sum += 222; sum += 223; sum += 224; sum += 225; sum += 226; sum += 227; sum += 228; sum += 229; sum += 230; 
        sum += 231; sum += 232; sum += 233; sum += 234; sum += 235; sum += 236; sum += 237; sum += 238; sum += 239; sum += 240; 
        sum += 241; sum += 242; sum += 243; sum += 244; sum += 245; sum += 246; sum += 247; sum += 248; sum += 249; sum += 250; 
        sum += 251; sum += 252; sum += 253; sum += 254; sum += 255; sum += 256; sum += 257; sum += 258; sum += 259; sum += 260; 
        sum += 261; sum += 262; sum += 263; sum += 264; sum += 265; sum += 266; sum += 267; sum += 268; sum += 269; sum += 270; 
        sum += 271; sum += 272; sum += 273; sum += 274; sum += 275; sum += 276; sum += 277; sum += 278; sum += 279; sum += 280; 
        sum += 281; sum += 282; sum += 283; sum += 284; sum += 285; sum += 286; sum += 287; sum += 288; sum += 289; sum += 290; 
        sum += 291; sum += 292; sum += 293; sum += 294; sum += 295; sum += 296; sum += 297; sum += 298; sum += 299; sum += 300; 
        sum += 301; sum += 302; sum += 303; sum += 304; sum += 305; sum += 306; sum += 307; sum += 308; sum += 309; sum += 310; 
        sum += 311; sum += 312; sum += 313; sum += 314; sum += 315; sum += 316; sum += 317; sum += 318; sum += 319; sum += 320; 
        sum += 321; sum += 322; sum += 323; sum += 324; sum += 325; sum += 326; sum += 327; sum += 328; sum += 329; sum += 330; 
        sum += 331; sum += 332; sum += 333; sum += 334; sum += 335; sum += 336; sum += 337; sum += 338; sum += 339; sum += 340; 
        sum += 341; sum += 342; sum += 343; sum += 344; sum += 345; sum += 346; sum += 347; sum += 348; sum += 349; sum += 350; 
        sum += 351; sum += 352; sum += 353; sum += 354; sum += 355; sum += 356; sum += 357; sum += 358; sum += 359; sum += 360; 
        sum += 361; sum += 362; sum += 363; sum += 364; sum += 365; sum += 366; sum += 367; sum += 368; sum += 369; sum += 370; 
        sum += 371; sum += 372; sum += 373; sum += 374; sum += 375; sum += 376; sum += 377; sum += 378; sum += 379; sum += 380; 
        sum += 381; sum += 382; sum += 383; sum += 384; sum += 385; sum += 386; sum += 387; sum += 388; sum += 389; sum += 390; 
        sum += 391; sum += 392; sum += 393; sum += 394; sum += 395; sum += 396; sum += 397; sum += 398; sum += 399; sum += 400; 
        sum += 401; sum += 402; sum += 403; sum += 404; sum += 405; sum += 406; sum += 407; sum += 408; sum += 409; sum += 410; 
        sum += 411; sum += 412; sum += 413; sum += 414; sum += 415; sum += 416; sum += 417; sum += 418; sum += 419; sum += 420; 
        sum += 421; sum += 422; sum += 423; sum += 424; sum += 425; sum += 426; sum += 427; sum += 428; sum += 429; sum += 430; 
        sum += 431; sum += 432; sum += 433; sum += 434; sum += 435; sum += 436; sum += 437; sum += 438; sum += 439; sum += 440; 
        sum += 441; sum += 442; sum += 443; sum += 444; sum += 445; sum += 446; sum += 447; sum += 448; sum += 449; sum += 450; 
        sum += 451; sum += 452; sum += 453; sum += 454; sum += 455; sum += 456; sum += 457; sum += 458; sum += 459; sum += 460; 
        sum += 461; sum += 462; sum += 463; sum += 464; sum += 465; sum += 466; sum += 467; sum += 468; sum += 469; sum += 470; 
        sum += 471; sum += 472; sum += 473; sum += 474; sum += 475; sum += 476; sum += 477; sum += 478; sum += 479; sum += 480; 
        sum += 481; sum += 482; sum += 483; sum += 484; sum += 485; sum += 486; sum += 487; sum += 488; sum += 489; sum += 490; 
        sum += 491; sum += 492; sum += 493; sum += 494; sum += 495; sum += 496; sum += 497; sum += 498; sum += 499; sum += 500; 
        sum += 501; sum += 502; sum += 503; sum += 504; sum += 505; sum += 506; sum += 507; sum += 508; sum += 509; sum += 510; 
        sum += 511; sum += 512; sum += 513; sum += 514; sum += 515; sum += 516; sum += 517; sum += 518; sum += 519; sum += 520; 
        sum += 521; sum += 522; sum += 523; sum += 524; sum += 525; sum += 526; sum += 527; sum += 528; sum += 529; sum += 530; 
        sum += 531; sum += 532; sum += 533; sum += 534; sum += 535; sum += 536; sum += 537; sum += 538; sum += 539; sum += 540; 
        sum += 541; sum += 542; sum += 543; sum += 544; sum += 545; sum += 546; sum += 547; sum += 548; sum += 549; sum += 550; 
        sum += 551; sum += 552; sum += 553; sum += 554; sum += 555; sum += 556; sum += 557; sum += 558; sum += 559; sum += 560; 
        sum += 561; sum += 562; sum += 563; sum += 564; sum += 565; sum += 566; sum += 567; sum += 568; sum += 569; sum += 570; 
        sum += 571; sum += 572; sum += 573; sum += 574; sum += 575; sum += 576; sum += 577; sum += 578; sum += 579; sum += 580; 
        sum += 581; sum += 582; sum += 583; sum += 584; sum += 585; sum += 586; sum += 587; sum += 588; sum += 589; sum += 590; 
        sum += 591; sum += 592; sum += 593; sum += 594; sum += 595; sum += 596; sum += 597; sum += 598; sum += 599; sum += 600; 
        sum += 601; sum += 602; sum += 603; sum += 604; sum += 605; sum += 606; sum += 607; sum += 608; sum += 609; sum += 610; 
        sum += 611; sum += 612; sum += 613; sum += 614; sum += 615; sum += 616; sum += 617; sum += 618; sum += 619; sum += 620; 
        sum += 621; sum += 622; sum += 623; sum += 624; sum += 625; sum += 626; sum += 627; sum += 628; sum += 629; sum += 630; 
        sum += 631; sum += 632; sum += 633; sum += 634; sum += 635; sum += 636; sum += 637; sum += 638; sum += 639; sum += 640; 
        sum += 641; sum += 642; sum += 643; sum += 644; sum += 645; sum += 646; sum += 647; sum += 648; sum += 649; sum += 650; 
        sum += 651; sum += 652; sum += 653; sum += 654; sum += 655; sum += 656; sum += 657; sum += 658; sum += 659; sum += 660; 
        sum += 661; sum += 662; sum += 663; sum += 664; sum += 665; sum += 666; sum += 667; sum += 668; sum += 669; sum += 670; 
        sum += 671; sum += 672; sum += 673; sum += 674; sum += 675; sum += 676; sum += 677; sum += 678; sum += 679; sum += 680; 
        sum += 681; sum += 682; sum += 683; sum += 684; sum += 685; sum += 686; sum += 687; sum += 688; sum += 689; sum += 690; 
        sum += 691; sum += 692; sum += 693; sum += 694; sum += 695; sum += 696; sum += 697; sum += 698; sum += 699; sum += 700; 
        sum += 701; sum += 702; sum += 703; sum += 704; sum += 705; sum += 706; sum += 707; sum += 708; sum += 709; sum += 710; 
        sum += 711; sum += 712; sum += 713; sum += 714; sum += 715; sum += 716; sum += 717; sum += 718; sum += 719; sum += 720; 
        sum += 721; sum += 722; sum += 723; sum += 724; sum += 725; sum += 726; sum += 727; sum += 728; sum += 729; sum += 730; 
        sum += 731; sum += 732; sum += 733; sum += 734; sum += 735; sum += 736; sum += 737; sum += 738; sum += 739; sum += 740; 
        sum += 741; sum += 742; sum += 743; sum += 744; sum += 745; sum += 746; sum += 747; sum += 748; sum += 749; sum += 750; 
        sum += 751; sum += 752; sum += 753; sum += 754; sum += 755; sum += 756; sum += 757; sum += 758; sum += 759; sum += 760; 
        sum += 761; sum += 762; sum += 763; sum += 764; sum += 765; sum += 766; sum += 767; sum += 768; sum += 769; sum += 770; 
        sum += 771; sum += 772; sum += 773; sum += 774; sum += 775; sum += 776; sum += 777; sum += 778; sum += 779; sum += 780; 
        sum += 781; sum += 782; sum += 783; sum += 784; sum += 785; sum += 786; sum += 787; sum += 788; sum += 789; sum += 790; 
        sum += 791; sum += 792; sum += 793; sum += 794; sum += 795; sum += 796; sum += 797; sum += 798; sum += 799; sum += 800; 
        sum += 801; sum += 802; sum += 803; sum += 804; sum += 805; sum += 806; sum += 807; sum += 808; sum += 809; sum += 810; 
        sum += 811; sum += 812; sum += 813; sum += 814; sum += 815; sum += 816; sum += 817; sum += 818; sum += 819; sum += 820; 
        sum += 821; sum += 822; sum += 823; sum += 824; sum += 825; sum += 826; sum += 827; sum += 828; sum += 829; sum += 830; 
        sum += 831; sum += 832; sum += 833; sum += 834; sum += 835; sum += 836; sum += 837; sum += 838; sum += 839; sum += 840; 
        sum += 841; sum += 842; sum += 843; sum += 844; sum += 845; sum += 846; sum += 847; sum += 848; sum += 849; sum += 850; 
        sum += 851; sum += 852; sum += 853; sum += 854; sum += 855; sum += 856; sum += 857; sum += 858; sum += 859; sum += 860; 
        sum += 861; sum += 862; sum += 863; sum += 864; sum += 865; sum += 866; sum += 867; sum += 868; sum += 869; sum += 870; 
        sum += 871; sum += 872; sum += 873; sum += 874; sum += 875; sum += 876; sum += 877; sum += 878; sum += 879; sum += 880; 
        sum += 881; sum += 882; sum += 883; sum += 884; sum += 885; sum += 886; sum += 887; sum += 888; sum += 889; sum += 890; 
        sum += 891; sum += 892; sum += 893; sum += 894; sum += 895; sum += 896; sum += 897; sum += 898; sum += 899; sum += 900; 
        sum += 901; sum += 902; sum += 903; sum += 904; sum += 905; sum += 906; sum += 907; sum += 908; sum += 909; sum += 910; 
        sum += 911; sum += 912; sum += 913; sum += 914; sum += 915; sum += 916; sum += 917; sum += 918; sum += 919; sum += 920; 
        sum += 921; sum += 922; sum += 923; sum += 924; sum += 925; sum += 926; sum += 927; sum += 928; sum += 929; sum += 930; 
        sum += 931; sum += 932; sum += 933; sum += 934; sum += 935; sum += 936; sum += 937; sum += 938; sum += 939; sum += 940; 
        sum += 941; sum += 942; sum += 943; sum += 944; sum += 945; sum += 946; sum += 947; sum += 948; sum += 949; sum += 950; 
        sum += 951; sum += 952; sum += 953; sum += 954; sum += 955; sum += 956; sum += 957; sum += 958; sum += 959; sum += 960; 
        sum += 961; sum += 962; sum += 963; sum += 964; sum += 965; sum += 966; sum += 967; sum += 968; sum += 969; sum += 970; 
        sum += 971; sum += 972; sum += 973; sum += 974; sum += 975; sum += 976; sum += 977; sum += 978; sum += 979; sum += 980; 
        sum += 981; sum += 982; sum += 983; sum += 984; sum += 985; sum += 986; sum += 987; sum += 988; sum += 989; sum += 990; 
        sum += 991; sum += 992; sum += 993; sum += 994; sum += 995; sum += 996; sum += 997; sum += 998; sum += 999; sum += 1000; 
        sum += 1001; sum += 1002; sum += 1003; sum += 1004; sum += 1005; sum += 1006; sum += 1007; sum += 1008; sum += 1009; sum += 1010; 
        sum += 1011; sum += 1012; sum += 1013; sum += 1014; sum += 1015; sum += 1016; sum += 1017; sum += 1018; sum += 1019; sum += 1020; 
        sum += 1021; sum += 1022; sum += 1023; sum += 1024; sum += 1025; sum += 1026; sum += 1027; sum += 1028; sum += 1029; sum += 1030; 
        sum += 1031; sum += 1032; sum += 1033; sum += 1034; sum += 1035; sum += 1036; sum += 1037; sum += 1038; sum += 1039; sum += 1040; 
        sum += 1041; sum += 1042; sum += 1043; sum += 1044; sum += 1045; sum += 1046; sum += 1047; sum += 1048; sum += 1049; sum += 1050; 
        sum += 1051; sum += 1052; sum += 1053; sum += 1054; sum += 1055; sum += 1056; sum += 1057; sum += 1058; sum += 1059; sum += 1060; 
        sum += 1061; sum += 1062; sum += 1063; sum += 1064; sum += 1065; sum += 1066; sum += 1067; sum += 1068; sum += 1069; sum += 1070; 
        sum += 1071; sum += 1072; sum += 1073; sum += 1074; sum += 1075; sum += 1076; sum += 1077; sum += 1078; sum += 1079; sum += 1080; 
        sum += 1081; sum += 1082; sum += 1083; sum += 1084; sum += 1085; sum += 1086; sum += 1087; sum += 1088; sum += 1089; sum += 1090; 
        sum += 1091; sum += 1092; sum += 1093; sum += 1094; sum += 1095; sum += 1096; sum += 1097; sum += 1098; sum += 1099; sum += 1100; 
        sum += 1101; sum += 1102; sum += 1103; sum += 1104; sum += 1105; sum += 1106; sum += 1107; sum += 1108; sum += 1109; sum += 1110; 
        sum += 1111; sum += 1112; sum += 1113; sum += 1114; sum += 1115; sum += 1116; sum += 1117; sum += 1118; sum += 1119; sum += 1120; 
        sum += 1121; sum += 1122; sum += 1123; sum += 1124; sum += 1125; sum += 1126; sum += 1127; sum += 1128; sum += 1129; sum += 1130; 
        sum += 1131; sum += 1132; sum += 1133; sum += 1134; sum += 1135; sum += 1136; sum += 1137; sum += 1138; sum += 1139; sum += 1140; 
        sum += 1141; sum += 1142; sum += 1143; sum += 1144; sum += 1145; sum += 1146; sum += 1147; sum += 1148; sum += 1149; sum += 1150; 
        sum += 1151; sum += 1152; sum += 1153; sum += 1154; sum += 1155; sum += 1156; sum += 1157; sum += 1158; sum += 1159; sum += 1160; 
        sum += 1161; sum += 1162; sum += 1163; sum += 1164; sum += 1165; sum += 1166; sum += 1167; sum += 1168; sum += 1169; sum += 1170; 
        sum += 1171; sum += 1172; sum += 1173; sum += 1174; sum += 1175; sum += 1176; sum += 1177; sum += 1178; sum += 1179; sum += 1180; 
        sum += 1181; sum += 1182; sum += 1183; sum += 1184; sum += 1185; sum += 1186; sum += 1187; sum += 1188; sum += 1189; sum += 1190; 
        sum += 1191; sum += 1192; sum += 1193; sum += 1194; sum += 1195; sum += 1196; sum += 1197; sum += 1198; sum += 1199; sum += 1200; 
        sum += 1201; sum += 1202; sum += 1203; sum += 1204; sum += 1205; sum += 1206; sum += 1207; sum += 1208; sum += 1209; sum += 1210; 
        sum += 1211; sum += 1212; sum += 1213; sum += 1214; sum += 1215; sum += 1216; sum += 1217; sum += 1218; sum += 1219; sum += 1220; 
        sum += 1221; sum += 1222; sum += 1223; sum += 1224; sum += 1225; sum += 1226; sum += 1227; sum += 1228; sum += 1229; sum += 1230; 
        sum += 1231; sum += 1232; sum += 1233; sum += 1234; sum += 1235; sum += 1236; sum += 1237; sum += 1238; sum += 1239; sum += 1240; 
        sum += 1241; sum += 1242; sum += 1243; sum += 1244; sum += 1245; sum += 1246; sum += 1247; sum += 1248; sum += 1249; sum += 1250; 
        sum += 1251; sum += 1252; sum += 1253; sum += 1254; sum += 1255; sum += 1256; sum += 1257; sum += 1258; sum += 1259; sum += 1260; 
        sum += 1261; sum += 1262; sum += 1263; sum += 1264; sum += 1265; sum += 1266; sum += 1267; sum += 1268; sum += 1269; sum += 1270; 
        sum += 1271; sum += 1272; sum += 1273; sum += 1274; sum += 1275; sum += 1276; sum += 1277; sum += 1278; sum += 1279; sum += 1280; 
        sum += 1281; sum += 1282; sum += 1283; sum += 1284; sum += 1285; sum += 1286; sum += 1287; sum += 1288; sum += 1289; sum += 1290; 
        sum += 1291; sum += 1292; sum += 1293; sum += 1294; sum += 1295; sum += 1296; sum += 1297; sum += 1298; sum += 1299; sum += 1300; 
        sum += 1301; sum += 1302; sum += 1303; sum += 1304; sum += 1305; sum += 1306; sum += 1307; sum += 1308; sum += 1309; sum += 1310; 
        sum += 1311; sum += 1312; sum += 1313; sum += 1314; sum += 1315; sum += 1316; sum += 1317; sum += 1318; sum += 1319; sum += 1320; 
        sum += 1321; sum += 1322; sum += 1323; sum += 1324; sum += 1325; sum += 1326; sum += 1327; sum += 1328; sum += 1329; sum += 1330; 
        sum += 1331; sum += 1332; sum += 1333; sum += 1334; sum += 1335; sum += 1336; sum += 1337; sum += 1338; sum += 1339; sum += 1340; 
        sum += 1341; sum += 1342; sum += 1343; sum += 1344; sum += 1345; sum += 1346; sum += 1347; sum += 1348; sum += 1349; sum += 1350; 
        sum += 1351; sum += 1352; sum += 1353; sum += 1354; sum += 1355; sum += 1356; sum += 1357; sum += 1358; sum += 1359; sum += 1360; 
        sum += 1361; sum += 1362; sum += 1363; sum += 1364; sum += 1365; sum += 1366; sum += 1367; sum += 1368; sum += 1369; sum += 1370; 
        sum += 1371; sum += 1372; sum += 1373; sum += 1374; sum += 1375; sum += 1376; sum += 1377; sum += 1378; sum += 1379; sum += 1380; 
        sum += 1381; sum += 1382; sum += 1383; sum += 1384; sum += 1385; sum += 1386; sum += 1387; sum += 1388; sum += 1389; sum += 1390; 
        sum += 1391; sum += 1392; sum += 1393; sum += 1394; sum += 1395; sum += 1396; sum += 1397;
        
        return sum;
    }
    
 
    public static void main(String[] args)
    {
        for (int i = 1; i < 1396; i++)
        {
            System.out.print("sum += " + i + "; ");
            
            if (i % 10 == 0)
            {
                System.out.print("\n");
            }
        }
    }
    
}
