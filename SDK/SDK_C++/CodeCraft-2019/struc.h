using namespace std;
struct Road{
    int id,len,speed,channel,from,to,duplex;
};
struct Car{
    int id,from,to,speed,plantime,gotime;
};
struct Cross{
    int id,roadid[4];
};
struct Answer{
    int carid,gotime,from;
    vector<int> roadid;
};
struct Edge{
    int roadid,to;
    double weight;
};
