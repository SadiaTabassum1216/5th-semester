#pragma once
#include<bits/stdc++.h>

#define EPS 1.0e-3
#define ERROR 404
#define catNum 13

using namespace std;

//dataset
struct treeDataset
{
    float information[catNum];
} users[4095];


struct tree
{
    int start;
    int end;
    bool isLeaf;
    bool decision;
    int splittingColumn;
} tree[5000];




int itr=0,leaf=0;
float mean[catNum-1];

//stack
int top=-1, stackArray[catNum-2];

//for push in string
void push(int a)
{
    top++;
    stackArray[top]=a;
}

//for pop in string
int pop()
{
    int a;
    a=stackArray[top];
    top--;
    return a;
}

int minIndex(float a[],int n)
{
    int i;
    int index=1;
    float m=a[1];
    for(i=1; i<n; i++)
    {
        if(a[i]<m)
        {
            m=a[i];
            index=i;
        }
    }
    return index;//the category number
}


float minEntropy(float a[],int n)
{
    int i;
    float m=a[1];
    for(i=1; i<n; i++)
    {
        if(a[i]<m)
        {
            m=a[i];
        }
    }
    return m;//min entropy
}

void meanCalculation(int n)
{
    int sum,i,j;
    for(j=1; j<catNum-1; j++)
    {
        sum=0;
        for(i=0; i<n; i++)
            sum=sum+users[i].information[j];

        mean[j]=sum/(float)n;
    }
    return;
}

float entropyValue(int num,int total)
{
    if(num==0 || num==total)
        return 0;
    float percentage=(float)num/(float)total;

    float entropy=-(percentage*log2(percentage)+(1-percentage)*log2(1-percentage));

    return entropy;
}




float* totalEntropy(int start,int end)
{
    float *entropy=new float[catNum-1];

    int i,j,countLow,countHigh,lowYes,lowNo,highYes,highNo;

    for(j=1; j<catNum-1; j++)
    {
        countLow=0,countHigh=0,lowYes=0,lowNo=0,highYes=0,highNo=0;
        for(i=start; i<=end; i++)
        {
            if(users[i].information[j]<mean[j])
            {
                countLow++;
                if(users[i].information[catNum-1]==1)
                    lowYes++;
                else
                    lowNo++;
            }
            else
            {
                countHigh++;
                if(users[i].information[catNum-1]==1)
                    highYes++;
                else
                    highNo++;
            }
        }


        entropy[j]=((float)countLow/(float)(end-start+1))*entropyValue(lowYes,countLow)+
                   ((float)countHigh/(float)(end-start+1))*entropyValue(highYes,countHigh);
    }

    return entropy;
}

int split(int start,int end, int splitColumn)
{
    int beforeIndex=start;
    int afterIndex=end;
    int i,j;

    float threshhold=mean[splitColumn];

    struct treeDataset copyDataset[end-start+1]; //temporary

    for(i=start,j=0; i<=end; i++,j++)
    {
        copyDataset[j]=users[i];
    }

    //partitioning
    for (i=start,j=0; i<end; i++,j++)
    {
        if(copyDataset[j].information[splitColumn]<threshhold)
        {
            users[beforeIndex]=copyDataset[j];
            beforeIndex++;
        }
        else
        {
            users[afterIndex]=copyDataset[j];
            afterIndex--;
        }
    }

    if(copyDataset[j].information[splitColumn]<threshhold)
    {
        users[beforeIndex]=copyDataset[j];
        return beforeIndex;
    }
    else
    {
        users[afterIndex]=copyDataset[j];
        return beforeIndex-1;
    }

}

void TreeConstruct(int start,int finish,int index)
{
    itr++;
    int i,counting;


    if(start>=finish || finish==-1)
    {
        tree[index].isLeaf=true;
        leaf++;
        if(users[start].information[catNum-1]==1)
        {
            tree[index].decision= true; //yes
            //cout<<boolalpha<<"Leaf "<<index<<": "<<tree[index].decision<<endl;
        }
        else
        {
            tree[index].decision= false; //no
            // cout<<boolalpha<<"Leaf "<<index<<": "<<tree[index].decision<<endl;
        }
        return;
    }

    float *entropy=totalEntropy(start,finish);

    //so that checked categories are ignored
    for(i=0; i<=top; i++)
    {
        entropy[stackArray[i]]=ERROR;
    }


    float minEntropyValue=minEntropy(entropy,catNum-1);  //min entropy
    int splitColumn=minIndex(entropy,catNum-1);   //index


    push(splitColumn);

    if(minEntropyValue<=EPS || top>=10)     //leaf
    {
        tree[index].isLeaf= true;
        leaf++;
        counting=0;

        for(i=start; i<finish; i++)
        {
            if(users[i].information[12]==1)
                counting++;
        }

        if(counting>=(finish-start-counting+1))
        {
            tree[index].decision= true; //yes
            //cout<<boolalpha<<"Leaf "<<index<<": "<<tree[index].decision<<endl;
        }
        else
        {
            tree[index].decision= false; //no
            // cout<<boolalpha<<"Leaf "<<index<<": "<<tree[index].decision<<endl;
        }

        pop();
        return;
    }

    //decision tree construct
    tree[index].start=start;
    tree[index].end=finish;
    tree[index].splittingColumn=splitColumn;
    tree[index].isLeaf=false;


    int midpoint=split(start,finish,splitColumn);

    if(start<=midpoint)
    {
        TreeConstruct(start,midpoint,2*index+1);
    }

    if(finish>midpoint)
    {
        TreeConstruct(midpoint+1,finish,2*index+2);
    }

    pop();
    return;

}

bool decision(float *userInput)
{
    int i,category,threshhold;
    for(i=0; tree[i].isLeaf==false;)
    {
        category=tree[i].splittingColumn;
        if(userInput[category]<mean[category])
            i=2*i+1;
        else
            i=2*i+2;
    }
   // cout<<"Decision Index: "<<i<<endl;

    return tree[i].decision;
}

void printTree(int n)
{
    int counter = 0,counter2=0;
    int level = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = level; j < n; j++)
        {
            cout << "\t";
        }
        for (int j = 0; j < pow(2, level); j++)
        {
            if (tree[counter].isLeaf)
            {
                cout << boolalpha << counter << ": D-" << tree[counter].decision<<"\t";
            }
            else if (tree[counter].splittingColumn > 0)
            {
                cout  << counter << ": SC-" << tree[counter].splittingColumn<<"\t";
            }
            counter++;
        }
//        cout << endl;
//         for (int j = level; j < n; j++)
//        {
//            cout << "      ";
//        }
//        for (int j = 0; j < pow(2, level); j++)
//        {
//            if (tree[counter2].splittingColumn > 0)
//            {
//                cout <<"/    \\      ";
//            }
//            counter2++;
//        }
        level++;
        cout << endl;
        cout << endl;
    }
}


void printOutput()
{
    int treeSize=pow(2,catNum)-1;

    for(int i=0; i<treeSize; i++)
    {
        if(tree[i].isLeaf==true)
        {
            cout<<boolalpha<<"index: "<<std::setw(4)<<i<<"\t\t\t\t\tDecision: "<<std::setw(4)<<tree[i].decision <<endl;
        }
        else if(tree[i].splittingColumn>0)
        {
            cout<<"index: "<<std::setw(4)<<i<<"\t\tSplit By: "<<tree[i].splittingColumn<<endl;
        }
    }
}
void decisionTree(string inputFile)
{
    int i,j,column,row,n;

        ifstream cin(inputFile);
        cin>>n>>column;
        row=n;


        float dataSet[row][catNum];

        for(i=0; i<row; i++)
        {
            for(j=0; j<=column; j++)
            {
                cin>>users[i].information[j];
                dataSet[i][j]=users[i].information[j];
            }
        }


    meanCalculation(n);
    TreeConstruct(0,n-1,0);

//    std::ofstream outFile("tree.txt");

//    // Write each struct element to the file
//    for (int i = 0; i < 10000; i++) {
//        outFile << tree[i].start << " ";
//        outFile << tree[i].end << " ";
//        outFile << tree[i].isLeaf << " ";
//        outFile << tree[i].decision << " ";
//        outFile << tree[i].splittingColumn << "\n";
//    }
//
//    // Close the file
//    outFile.close();

}

void createDecisionTree(string inputFile){
    decisionTree(inputFile);
   // printTree(catNum);
}
