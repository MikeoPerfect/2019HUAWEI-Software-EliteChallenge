#include"iostream"
#include "func.h"
using namespace std;
int main(int argc, char *argv[])
{
    cout << "Begin" <<endl;

//	if(argc < 5){
//		cout << "please input args: carPath, roadPath, crossPath, answerPath" << endl;
//		exit(1);
//	}

//	string carPath(argv[1]);
//	string roadPath(argv[2]);
//	string crossPath(argv[3]);
//	string answerPath(argv[4]);
	string carPath("car.txt");
	string roadPath("road.txt");
	string crossPath("cross.txt");
	string answerPath("answer.txt");
	cout << "carPath is " << carPath << endl;
	cout << "roadPath is " << roadPath << endl;
	cout << "crossPath is " << crossPath << endl;
	cout << "answerPath is " << answerPath << endl;

	// TODO:read input filebuf
	// TODO:process
	// TODO:write output file

	writeanstxt(answerPath,solvedij(readcartxt(carPath),readcrosstxt(crossPath),readroadtxt(roadPath)));
	return 0;
}
