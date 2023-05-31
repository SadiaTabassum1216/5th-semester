#include "input.h"
#include "form.h"
#include "decisionTree.h"

int main()
{
    int i;
    cout<<"\t\t**LOAN APPROVAL ESTIMATOR**"<<endl;
    datasetCreator("Train-Dataset.txt","output.txt");

    formFillup("output.txt");

}
