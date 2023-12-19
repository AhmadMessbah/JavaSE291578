package mft;

import mft.model.entity.Receipt;

public class Main {
    public static void main(String[] args)throws Exception {
    /* Person person = Person.builder().name("reza").family("rezaii").build();

    PersonDa personDa = new PersonDa();
         System.out.println(personDa.save(person));

      System.out.println(person + " Saved");
     personDa.edit(person);
      System.out.println(person + " Edited");
      personDa.remove(2);
      System.out.println("Person id : 2 Removed");
      System.out.println(personDa.findAll());
      System.out.println(personDa.findById(2));
      System.out.println(personDa.findByFamily("messbah"));
     Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter Family : ");
    String family = scanner.nextLine();

  System.out.println( PersonController.save(name, family));
*/
//------------------------------------------------

        //      User user= User.builder().username("farnoosh").password("12396564").active(true).build();
        //    System.out.println(UserController.save(user.getUsername(), user.getPassword(), user.isActive()));

        //   UserDa userDa = new UserDa();
//          userDa.save(user);
//          System.out.println(user + " Saved");

     //   User user1 = User.builder().id(1).username("leila").password("546645").active(false).build();
     //   userDa.edit(user1);
     //   System.out.println(user1 + " Edited");

       // userDa.remove(2);
   //     System.out.println("User id : 2 Removed");

     //   System.out.println(userDa.findAll());
     //   System.out.println(userDa.findById(3));
  //      System.out.println(userDa.findByUsername("farnoosh"));

//---------------------------------------------------------------------------------
    // Receipt receipt =  Receipt
        //        .builder().amount(321)
         //       .description("LevaZiaei").build();
  //  ReceiptDa receiptDa=new ReceiptDa();
    //  receiptDa.save(receipt);
      //  System.out.println(receipt + "Saved");
       // Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter Amount : ");
       //String amount = scanner.nextLine();

        //System.out.print("Enter description : ");
        //String description = scanner.nextLine();

        //System.out.print(ReceiptController.save(amount ,description));
        Receipt receipt =  Receipt
               .builder().id(2)
                .amount(String.valueOf(369))
               .description("PrAr").build();
          ReceiptDa receiptDa=new ReceiptDa();
        //receiptDa.edit(receipt);
        //System.out.println(receipt + " Edited");
      //  receiptDa.remove(3);
      //  System.out.println("User id : 3 Removed");
      //  System.out.println(receiptDa.findAll());
      //  System.out.println(receiptDa.findById(24));


    }}