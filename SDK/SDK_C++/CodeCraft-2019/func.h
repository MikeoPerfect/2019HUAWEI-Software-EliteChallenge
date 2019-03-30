#include"iostream"
#include"cstdio"
#include"cstdlib"
#include"vector"
#include"algorithm"
#include"map"
#include"queue"
#include "struc.h"
#define INF 99999
#define LL long long
#define pll (pair<long long,long long>
#define pb push_back
#define fi first
#define se second
using namespace std;
typedef pair<int,int> pii;
typedef pair<double,int> pdi;
vector<Car> readcartxt(string carpath)
{
//    cout<<"car"<<endl;
    FILE *fin;
    fin = fopen(carpath.c_str(),"r");
    vector<Car> car;
    int id,from,to,speed,plantime;
    char s[200];
    fgets(s,200,fin);
    while(fscanf(fin," (%d, %d, %d, %d, %d)",&id,&from,&to,&speed,&plantime)!=EOF)
        car.pb((Car){id,from,to,speed,plantime});
    fclose(fin);
    return car;
}
vector<Road> readroadtxt(string roadpath)
{
//    cout<<"road"<<endl;
    FILE *fin;
    fin = fopen(roadpath.c_str(),"r");
    vector<Road> road;
    int id,len,speed,channel,from,to,duplex;
    char s[200];
    fgets(s,200,fin);
    while(fscanf(fin," (%d, %d, %d, %d, %d, %d, %d)",&id,&len,&speed,&channel,&from,&to,&duplex)!=EOF)
        road.pb((Road){id,len,speed,channel,from,to,duplex});
    fclose(fin);
    return road;
}
vector<Cross> readcrosstxt(string crosspath)
{
//    cout<<"cross"<<endl;
    FILE *fin;
    fin = fopen(crosspath.c_str(),"r");
    vector<Cross> cross;
    int id,roadid[4];
    char s[200];
    fgets(s,200,fin);
    while(fscanf(fin," (%d, %d, %d, %d, %d)",&id,&roadid[0],&roadid[1],&roadid[2],&roadid[3])!=EOF)
        cross.pb((Cross){id,roadid[0],roadid[1],roadid[2],roadid[3]});
    fclose(fin);
    return cross;
}
void writeanstxt(string anspath,vector<Answer> answer)
{
//    cout<<"write"<<endl;
    sort(answer.begin(),answer.end(),[](Answer a,Answer b){return a.carid<b.carid;});
    FILE *fout;
    fout = fopen(anspath.c_str(),"w");
    for(auto iter:answer)
    {
        fprintf(fout,"(%d, %d",iter.carid,iter.gotime);
        for(auto iter2:iter.roadid)
            fprintf(fout,", %d",iter2);
        fprintf(fout,")\n");
    }
    fclose(fout);
    return ;
}

//简单的dij实现最短路求解
void dijkstra(int s,int m,vector<double> &dis,vector<int> &precro,vector<int> &preroad,vector<vector<Edge> >  &edge)
{
    fill(dis.begin(),dis.end(),INF);
    priority_queue<pdi,vector<pdi>,greater<pdi> > que;
    dis[s]=0.0;
    precro[s]=s;
    preroad[s]=-1;
    que.push(pdi(0.0,s));
    while(!que.empty())
    {
        pii p=que.top();
        que.pop();
        int v=p.se;
        if(dis[v]<p.fi)  continue;
        for(auto e:edge[v])
        {
            if(dis[e.to]>dis[v]+e.weight)
            {
                dis[e.to]=dis[v]+e.weight;
                precro[e.to]=v;
                preroad[e.to]=e.roadid;
                que.push(pii(dis[e.to],e.to));
            }
        }
    }
    return ;
}
vector<Answer> solvedij(vector<Car> car,vector<Cross> cross,vector<Road> road)
{
    //建节点
    int n =car.size();
    int m=cross.size();
    int k = road.size();
    map<int,int> crosstoind;
    map<int,int> indtocross;
    for(int i=0;i<m;i++)
        crosstoind[cross[i].id]=i,indtocross[i]=cross[i].id;
    //建立车库
    vector<vector<int> > garage(m,vector<int>(0));
    for(int i=0;i<n;i++)
    {
        int from = crosstoind[car[i].from];
        garage[from].pb(i);
    }
    //建边
    vector<vector<Edge> >  edge(m,vector<Edge>(0));
    for(int i=0;i<k;i++)
    {
        int from = crosstoind[road[i].from];
        int to = crosstoind[road[i].to];
        int id = road[i].id;
        int len = road[i].len;
        double weight = road[i].len*1.0/road[i].speed;
        int duplex = road[i].duplex;
        edge[from].pb((Edge){id,to,weight});
        if(duplex)  edge[to].pb((Edge){id,from,weight});
    }
    //dij实现
    vector<Answer> answer;
    for(int i=0;i<m;i++)
    {
        if(garage[i].size()>0)
        {
            vector<int> precro(m),preroad(m);
            vector<double> dis(m);
            dijkstra(i,m,dis,precro,preroad,edge);
            for(auto carind : garage[i])
            {
                int to = crosstoind[car[carind].to];
                vector<int> roadid;
                int vis = to;
                while(vis != i)
                {
                    roadid.pb(preroad[vis]);
                    vis = precro[vis];
                }
                reverse(roadid.begin(),roadid.end());
                answer.pb((Answer){car[carind].id,car[carind].plantime,car[carind].from,roadid});
                car[carind].gotime = car[carind].plantime;
            }
        }
    }
    sort(answer.begin(),answer.end(),[](Answer a,Answer b){return a.gotime < b.gotime;});
    vector<int> roadlist[k];
    map<int,int> cartoind;
    map<int,int> indtocar;
    for(int i=0;i<n;i++)
        cartoind[car[i].id]=i,indtocar[i]=car[i].id;
    map<int,int> roadtoind;
    map<int,int> indtoroad;
    for(int i=0;i<k;i++)
        roadtoind[road[i].id]=i,indtoroad[i]=road[i].id;
    int pretime=0,nowtime=0;
    int wei1 = 3;
    int wei2 = 1;
    int allct = 0;
    for(int i=0;i<n;i++)
    {
        if(answer[i].roadid.size()==0) continue;
        int roadid = answer[i].roadid[0];
        int carid = answer[i].carid;
        int index = roadtoind[roadid];
        if(allct>20 &&((road[index].channel<=2 && roadlist[index].size() == road[index].channel) || (road[index].channel>2 && roadlist[index].size() == road[index].channel - 1)))
        {
            int maxtime = pretime;
            int ct = 0;
            for(int j=0;j<k;j++)
            {
                for(int l=0;l<roadlist[j].size();l++)
                {
                    int carindex = cartoind[roadlist[j][l]];
                    maxtime = max(maxtime,car[carindex].gotime);
                    ct++;
                }
            }
            for(int j=0;j<k;j++)
            {
                for(int l=0;l<roadlist[j].size();l++)
                {
                    int carindex = cartoind[roadlist[j][l]];
                    car[carindex].gotime = maxtime;
                }
                roadlist[j].clear();
            }
            pretime = maxtime+2;
            allct -= ct;
        }
        roadlist[index].pb(carid);
    }
    int maxtime = pretime;
    for(int j=0;j<k;j++)
    {
        for(int l=0;l<roadlist[j].size();l++)
        {
            int carindex = cartoind[roadlist[j][l]];
            maxtime = max(maxtime,car[carindex].gotime);
        }
    }
    for(int j=0;j<k;j++)
    {
        for(int l=0;l<roadlist[j].size();l++)
        {
            int carindex = cartoind[roadlist[j][l]];
            car[carindex].gotime = maxtime;
        }
    }
    for(int i=0;i<n;i++)
    {
        int carindex = cartoind[answer[i].carid];
        answer[i].gotime = car[carindex].gotime;
    }
    return answer;
}
