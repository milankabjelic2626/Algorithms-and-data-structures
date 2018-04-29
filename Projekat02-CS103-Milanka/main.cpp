#include <iostream>
#include <string>
#include <fstream>
using namespace std;

struct knjiga
{
    string naslov;
    string autor;
    string izdavac;
    int godinaIzdavanja;
    string barKod;
    knjiga* next;
    knjiga(string, string, string, int, string, knjiga*);
};

knjiga::knjiga(string tempNaslov, string tempAutor, string tempIzdavac, int tempGodinaIzdavanja, string tmpBarKod, knjiga* tempNext)
    :naslov(tempNaslov), autor(tempAutor), izdavac(tempIzdavac), godinaIzdavanja(tempGodinaIzdavanja), barKod(tmpBarKod), next(tempNext){}

typedef knjiga* knjigaPtr;//pointer tipa strukture knjiga


void getline(istream &stream, string &str, char granica)//funkcija za uzimanje linije iz txt fajla i njeno pakovanje u string
{	char temp[50];
    
    stream.get(temp, 50, granica);
    stream.ignore(50, granica);
    str = temp;
}

void getline(istream &stream, int &broj, char granica)//funkcija za uzimanje linije iz txt fajla i njeno pakovanje u broj
{	int temp;
    
    stream >> temp;
    stream.ignore(50, granica);
    broj= temp;
}

void citajFajl(knjigaPtr &root);
void dodaj (knjigaPtr &root);
void izbrisiPoNaslovu(knjigaPtr &root);
knjigaPtr locirajCvor(knjigaPtr temp, string tit);
void izbrisiPoBarKodu(knjigaPtr &root);
knjigaPtr locirajCvorPoBarKodu(knjigaPtr temp, string isb);
void pretraziPoBarKodu(knjigaPtr temp);
void ispisiSve(knjigaPtr temp);
void ispisiKnjigeAutora(knjigaPtr temp);
void sacuvajFajl(knjigaPtr temp);
int prebrojCvorove(knjigaPtr temp);
void sortingAutor (knjigaPtr temp);
//void autorSorting (knjigaPtr temphead);

int main()
{
    int izbor;
    knjigaPtr root = NULL;
    citajFajl(root);
    
    do
    {
        cout << "Izaberite jednu od ponudjenih opcija\n\n";
        cout << "1: Dodajte knjigu u listu\n";
        cout << "2: Izbrisi knjigu na osnovu naslova\n";
        cout << "3: Izbrisi knjigu na osnovu bar koda\n";
        cout << "4: Nadji knjigu pomocu bar koda\n";
        cout << "5: Ispisi listu svih knjiga\n";
        cout << "6: Ispisi listu svih knjiga odredjenog autora\n";
        cout << "7: Izlaz\n\n";
        cout << "Izbor ---> ";
        
        cin >> izbor;
        
        if (1 <= izbor && izbor <= 6)
        {
            switch (izbor)
            {
                case 1:
                    dodaj(root);
                    break;
                case 2:
                    izbrisiPoNaslovu(root);
                    break;
                case 3:
                    izbrisiPoBarKodu(root);
                    break;
                case 4:
                    pretraziPoBarKodu(root);
                    break;
                case 5:
                    ispisiSve(root);
                    break;
                case 6:
                    ispisiKnjigeAutora(root);
                    break;
                default:
                    cout << "Greska pri izboru, pokusajte ponovo:\n\n";
                    break;
            }
        }
    }
    while (izbor != 7);
    sacuvajFajl(root);
    return 0;
}

void citajFajl(knjigaPtr &root)
{
    int numBooks, yea;
    string tit, aut, pub, isb;
    ifstream infile ("books.txt", ios::in);
    infile >> numBooks;
    infile.ignore(50,'\n');
    for (int count = 0; count < numBooks; count++)
    {
        getline(infile, tit, '\n');
        getline(infile, aut, '\n');
        getline(infile,pub, '\n');
        getline(infile,yea, '\n');
        getline(infile, isb, '\n');
        
        root = new knjiga (tit, aut, pub, yea, isb, root);
    }
}

void dodaj(knjigaPtr &root)
{
    string tit, aut, pub, isb;
    int yea;
    
    cout << "Naslov:\t\t\t";
    cin.ignore(50,'\n');
    getline(cin, tit, '\n');
    cout << "Autor:\t\t\t";
    getline(cin, aut, '\n');
    cout << "Izdavac:\t\t";
    getline(cin,pub, '\n');
    cout << "Godina izdavanja:\t\t\t";
    getline(cin,yea, '\n');
    cout << "Bar-kod:\t\t\t";
    getline(cin, isb, '\n');
    
    root = new knjiga (tit, aut, pub, yea, isb, root);
}

void izbrisiPoNaslovu(knjigaPtr &root)
{
    string tit;
    
    cout << "Naslov knjige:\t\t\t";
    cin.ignore(50,'\n');
    getline(cin, tit, '\n');
    
    knjigaPtr p = locirajCvor(root, tit);
    
    if (p == NULL)
        cout << "\nBrisanje nije uspelo\n\n";
    else if (root == p)
        root = p->next;
    else
    {
        knjigaPtr q = root;
        while (q->next != p)
            q = q->next;
        q->next = p->next;
    }
    delete p;
}

knjigaPtr locirajCvor(knjigaPtr temp, string tit)
{
    while (temp != NULL)
    {
        if (temp->naslov == tit)
        {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

void izbrisiPoBarKodu(knjigaPtr &root)
{
    string isb;
    
    cout << "Bar-kod knjige:\t\t\t";
    cin.ignore(50,'\n');
    getline(cin, isb, '\n');
    
    knjigaPtr p = locirajCvorPoBarKodu(root, isb);
    
    if (p == NULL)
        cout << "\nBrisanje nije uspelo\n\n";
    else if (root == p)
        root = p->next;
    else
    {
        knjigaPtr q = root;
        while (q->next != p)
            q = q->next;
        q->next = p->next;
    }
    delete p;
}

knjigaPtr locirajCvorPoBarKodu(knjigaPtr temp, string isb)
{
    while (temp != NULL)
    {
        if (temp->barKod == isb)
        {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

void pretraziPoBarKodu(knjigaPtr temp)
{
    string isb;
    
    cout << "Bar-kod knjige:\t\t\t";
    cin.ignore(50,'\n');
    getline(cin, isb, '\n');
    
    while (temp != NULL)
    {
        if (isb == temp->barKod)
        {
            cout << temp->naslov << "\n";
            cout << temp->autor << "\n";
            cout << temp->izdavac << "\n";
            cout << temp->godinaIzdavanja << "\n";
            cout << temp->barKod << "\n\n";
        }
        temp = temp->next;
    }
    cout << "\n";
}

void ispisiSve(knjigaPtr temp)
{
    while (temp != NULL)
    {
        cout << temp->naslov << "\n";
        cout << temp->autor << "\n";
        cout << temp->izdavac << "\n";
        cout << temp->godinaIzdavanja << "\n";
        cout << temp->barKod << "\n\n";
        temp = temp->next;
    }
    cout << "\n";
}

void ispisiKnjigeAutora(knjigaPtr temp)
{
    string aut;
    
    cout << "Ime autora:\t\t\t";
    cin.ignore(50,'\n');
    getline(cin, aut, '\n');
    
    while (temp != NULL)
    {
        if (temp->autor == aut)
        {
            cout << temp->naslov << "\n";
            cout << temp->autor << "\n";
            cout << temp->izdavac << "\n";
            cout << temp->godinaIzdavanja << "\n";
            cout << temp->barKod << "\n\n";
        }
        temp = temp->next;
    }
    cout << "\n";
}

void sacuvajFajl(knjigaPtr temp)
{
    int count = prebrojCvorove(temp);
    ofstream outFile("saved.txt",ios::out);
    
    outFile << count << "\n";
    while (temp != NULL)
    {
        outFile << temp->naslov << "\n";
        outFile << temp->autor << "\n";
        outFile << temp->izdavac << "\n";
        outFile << temp->godinaIzdavanja << "\n";
        outFile << temp->barKod << "\n";
        temp = temp->next;
    }
    cout << "\n";
}

int prebrojCvorove(knjigaPtr temp)
{
    int brojac = 0;
    while (temp != NULL)
    {
        brojac++;
        temp = temp->next;
    }
    return brojac;
}



/*
void SortiranePoAutoru(knjigaPtr temp)
{
    string aut;

    getline(cin, aut, '\n');
    
    while (temp != NULL)
    {
        if (temp->autor == aut)
        {
            cout << temp->naslov << "\n";
            cout << temp->autor << "\n";
            cout << temp->izdavac << "\n";
            cout << temp->godinaIzdavanja << "\n";
            cout << temp->barKod << "\n\n";
        }
        temp = temp->next;
    }
    cout << "\n";
}


 void InsertionSort(knjiga k[], int size )
 {
     int j;
     knjiga temp;
     for(int i = 1; i < size; i++)
     {
         j = i - 1;
         while( j >= 0 && strcmp( k[j+1]->autor, k[j].name ) < 0 )
         {
             temp =  k[j + 1];
             k[j+1] = k[j];
             k[j] = temp;
             j--;
         }
     }
 }
 

void autorSorting (knjigaPtr &root)
{
    //string temproll;
    string tempname;
    int counter = 0;
    while (root)
    {
        root = root->next;
        counter++;
    }
    root = root;
    
    for (int j=0; j<counter; j++)
    {
        while (root->next)
        {
            if (root->autor > temphead->next->autor)
            {
                //temproll = temphead->roll;
                //temphead->roll = temphead->next->roll;
                //temphead->next->roll = temproll;
                
                tempname = temphead->autor;
                temphead->autor = temphead->next->autor;
                temphead->next->autor = tempname;
            }
            temphead = temphead->next;
        }
        temphead = root;
    }
}
*/

