#include<bits/stdc++.h>
#include "decisionTree.h"
#define catNum 13
#define k 5


using namespace std;

int userNum;

//dataset
struct testUser
{
    float information[catNum];
} testUsers[5000],train[5000],test[1000];

void shuffle()
{
    int i,j;
    struct testUser temp;
    srand(time(0));
    for (i=0; i<userNum; i++)
    {
        j=rand()%userNum;
        temp=testUsers[i];
        testUsers[i]=testUsers[j];
        testUsers[j]=temp;
    }
}

void printFolderData(struct testUser *client, int size)
{
    int i,j;
    for(i=0; i<size; i++)
    {
        for(j=0; j<13; j++)
        {
            cout<<client[i].information[j]<<"  ";
        }
        cout<<endl;
    }
}

using namespace std;

//main

void kFoldValidation(string input)//total file
{
    int i,j,l,m,itr,itr2,row,column;

    {
        ifstream cin(input);
        cin>>row>>column;
        userNum=row;
        //n=15;

        for(i=0; i<row; i++)
        {
            for(j=0; j<=column; j++)
            {
                cin>>testUsers[i].information[j];
            }
        }
    }


    cout<<"Total customer is: "<<userNum<<endl;
    cout<<"Number of groups(k value): "<<k<<endl;


    shuffle();


    int userPerGroup=userNum/k;
    cout<<"User per group: "<<userPerGroup<<endl;
    int start=0,end=-1;

    for(i=0; i<k; i++)
    {
        start=end+1;
        end=start+userPerGroup-1;
        cout<<"\nGroup:"<<i+1<<"\n------------\nStart: "<<start<<"\tEnd: "<<end<<endl;


        for(l=0; l<start; l++)
        {
            for(j=0; j<=column; j++)
            {
                train[l].information[j]=testUsers[l].information[j];
            }
        }
        for(l=start,m=0; l<=end; l++,m++)
        {
            for(j=0; j<=column; j++)
            {
                test[m].information[j]=testUsers[l].information[j];
            }
        }
        for(l=end+1,m=start; l<userNum; l++,m++)
        {
            for(j=0; j<=column; j++)
            {
                train[m].information[j]=testUsers[l].information[j];
            }
        }

        //write a train file

            ofstream file("train.txt");
            file<<userNum-userPerGroup<<"\t"<<"12"<<endl;

            for(itr=0; itr<(userNum-userPerGroup); itr++)
            {
                for(itr2=0; itr2<13; itr2++)
                    file<<setw(10)<<train[itr].information[itr2];
                file<<endl;
            }
            file.close();



        //write a test file

            ofstream file2("test.txt");
            file2<<userPerGroup<<"\t"<<"12"<<endl;

            for(itr=0; itr<userPerGroup; itr++)
            {
                for(itr2=0; itr2<13; itr2++)
                    file2<<setw(10)<<test[itr].information[itr2];
                file2<<endl;
            }
            file2.close();

        float temp[13];
        int count=0;
        createDecisionTree("test.txt");


        for(itr=0; itr<userPerGroup; itr++)
        {
            for(itr2=0; itr2<13; itr2++)
            {
                temp[itr2]=test[itr].information[itr2];
            }
            bool output=decision(temp);
            if((output==true && temp[12]==1) || (output==false && temp[12]==0))
                count++;
        }

        float rate=(float)count/(float)userPerGroup*100;
        cout<<"Rate of fold "<<i+1<<" is: "<<rate<<"%"<<endl;

    }

    return;
}
