#include<stdio.h>
//#include<conio.h>
#include<string.h>
#include<stdlib.h>
//#include<dos.h>




struct Kontakt
{
    long broj;
    char ime[20],adresa[20],email[30];
}lista;

char trazen[20],ime[20];
FILE *fk, *fn;
int i,n,ch,len,pronadjen;

int main()
{
    
main:
    system("cls");    /* GLAVNI MENI */
    printf("\n\t\t **** Imenik ****");
    printf("\n\n\n\t\t    GLAVNI MENI\n\t\t=====================\n\t\t[1] Dodaj kontakt\n\t\t[2] Lista kontakata\n\t\t[3] Nadji kontakt\n\t\t[4] Uredi kontakt\n\t\t[5] Obrisi kontakt\n\t\t[0] Izlaz\n\t\t=================\n\t\t");
    printf("Unesi izbor:");
    scanf("%d",&ch);
    
    switch(ch)
    {
        case 0:
            printf("\n\n\t\tDa li ste sigurni da zelite da naputite aplikaciju?");
            break;
            
            /* DODAVANJE KONTAKTA  */
        case 1:
            system("cls");
            fk=fopen("kontakti.txt","a");
            for (;;)
            {
                fflush(stdin);
                printf("Da biste izasli ukucajte razmak u polje za ime.\nIme:");
                scanf("%[^\n]",&lista.ime);
                if(stricmp(lista.ime,"")==0 || stricmp(lista.ime," ")==0)
                    break;
                fflush(stdin);
                printf("Broj telefona:");
                scanf("%ld",&lista.broj);
                fflush(stdin);
                printf("Adresa:");
                scanf("%[^\n]",&lista.adresa);
                fflush(stdin);
                printf("Email adresa:");
                scanf("%[^\n]",&lista.email);
                printf("\n");
                fwrite(&lista,sizeof(lista),1,fk);
            }
            fclose(fk);
            break;
            
            /* LISTA KONTAKATA  */
        case 2:
            system("cls");
            printf("\n\t\t================================\n\t\t\tLISTA KONTAKATA\n\t\t================================\n\n\n====================================================================\n\n");
            for(i=97;i<=122;i++)
            {
                fk=fopen("kontakti.txt","r");
                fflush(stdin);
                pronadjen=0;
                while(fread(&lista,sizeof(lista),1,fk)==1)
                {
                    if(lista.ime[0]==i || lista.ime[0]==i-32)
                    {
                        printf("\nIme: \t\t %s\nBroj telefona:\t 0%ld\nAdresa:\t\t %s\nEmail:\t\t %s\n",lista.ime,
                               lista.broj,lista.adresa,lista.email);
                        pronadjen++;
                    }
                }
                if(pronadjen!=0)
                {
                    printf("=========================================================== [%c]- (%d)\n\n",i-32,pronadjen);
                    getch();
                }
                fclose(fk);
            }
            break;
            
            
            
            /*  NA–I KONTAKT  */
        case 3:
            system("cls");
            do
            {
                system("cls");
                pronadjen=0;
                printf("\n\n\t..::NADJI KONTAKT\n\t===========================\n\t..::Ime kontakta koji treba pronaci: ");
                fflush(stdin);
                scanf("%[^\n]",&trazen);
                len=strlen(trazen);
                fk=fopen("kontakti.txt","r");
                system("cls");
                printf("\n\n..::Trazi: '%s' \n===================================================\n",trazen);
                while(fread(&lista,sizeof(lista),1,fk)==1)
                {
                    for(i=0;i<=len;i++)
                        ime[i]=lista.ime[i];
                    ime[len]='\0';
                    if(stricmp(ime,trazen)==0)
                    {
                        printf("\n..::Ime: %s\n..::Broj telefona: 0%ld\n..::Adresa: %s\n..::Email: %s\n",lista.ime,lista.broj,lista.adresa,lista.email);
                        pronadjen++;
                        getch();
                    }
                }
                if(pronadjen==0)
                    printf("\n..::Nije pronadjen kontakt!");
                else
                {
                    if (pronadjen==1)
                        printf("\n..::%d kontakt pronadjen.",pronadjen);
                    else
                        printf("\n..::%d kontakta pronadjeno.",pronadjen);
                    fclose(fk);
                }
                printf("\n ..::Da li zelite ponovo da trazite kontakt?\n\n\t[1] DA\t\t[0] NE\n\t");
                scanf("%d",&ch);
            }
            while(ch==1);
            break;
            
            
            /* UREDI KONTAKTE */
        case 4:
            system("cls");
            fk=fopen("kontakti.txt","r");
            fn=fopen("nov.dat","w");
            fflush(stdin);
            printf("..::UREDI KONTAKT\n===============================\n\n\t..::Unesite ime kontakta koji zelite da uredite:");
            scanf("%[^\n]",ime);
            while(fread(&lista,sizeof(lista),1,fk)==1)
            {
                if(stricmp(ime,lista.ime)!=0)
                {
                    fwrite(&lista,sizeof(lista),1,fn);
                }
                fflush(stdin);
                printf("\n\n..::Uredjivanje '%s'\n\n",ime);
                printf("..::Ime:");
                scanf("%[^\n]",&lista.ime);
                fflush(stdin);
                printf("..::Broj telefona:");
                scanf("%ld",&lista.broj);
                fflush(stdin);
                printf("..::Adresa:");
                scanf("%[^\n]",&lista.adresa);
                fflush(stdin);
                printf("..::Email adresa:");
                gets(lista.email);
                printf("\n");
                fwrite(&lista,sizeof(lista),1,fn);
                fclose(fk);
                fclose(fn);
                remove("kontakti.txt");
                rename("nov.dat","kontakti.txt");
            } 
            break;
            
            
            /* BRISANJE KONTAKATA */
        case 5:
            system("cls");
            fflush(stdin);
            printf("\n\n\t..::OBRISI KONTAKT\n\t==========================\n\t..::Unesite ime kontakta koji zelite da izbrisete:");
            scanf("%[^\n]",&ime);
            fk=fopen("kontakti.txt","r");
            fn=fopen("nov.dat","w");
            while(fread(&lista,sizeof(lista),1,fk)!=0)
                if (stricmp(ime,lista.ime)!=0)
                    fwrite(&lista,sizeof(lista),1,fn);
            fclose(fk);
            fclose(fn);
            remove("kontakti.txt");
            rename("nov.dat","kontakti.txt");
            break;
            
        default:
            printf("Pogresan unos!");
            break;
            
    }
    printf("\n\n\n..::Unesite izbor:\n\n\t[1] Glavni meni\t\t[0] Izlaz\n");
    scanf("%d",&ch);
    switch (ch)
    {
        case 1:
            goto main;
            
            
        case 0:
            break;
            
        default:
            printf("Pogresan unos!");
            break;
    }
    
    return 0;
}
