insert into products (id,title, description, image, price, quantity) values
(1,'шашлык','шашлык из курицы, кг',
'https://adygsalt.ru/blog/foto/marinad-dlya-shashlyika-iz-kuricyi/0LtLjDg-z3o.jpg',
5000, 10),
(2,'помидоры','помидоры блин, кг',
'https://avatars.dzeninfra.ru/get-zen_doc/3665883/pub_62474d699fe0ef42e5bd4dcc_62474e1553149161c2b69a68/scale_1200',
360, 3),
(3,'огурцы','описание огурцов, кг',
 'https://newogorod.ru/wp-content/uploads/2019/08/d0e2b75f1d6f24860382a42941541342.jpg',
 300, 3),
(4,'лук','лук репчатый, кг',
 'https://1000dosok.ru/s/09-08-4189456.jpg',
 100, 5),
(5,'маринад','маринад для шашлыка, шт',
 'https://basket-04.wb.ru/vol552/part55258/55258892/images/big/1.jpg',
 500, 2),
(6,'водичка','вода питьевая, л',
 'http://milk.hrodna.biz/upload/medialibrary/e5f/u38r39s1vxu8p40dvxhz5kpzbyxi0fh2.jpg',
 700, 15),
(7,'свинные уши','название говорит за себя, кг',
 'https://sun9-73.userapi.com/tevvr542alEgC2QkZop1Hqg-m9f39LKMicOXKw/7GT5QBCumM8.jpg',
 10000, 1),
(8,'живое пиво','пиво живое, л',
 'https://avatars.mds.yandex.net/i?id=fdc56019bcf681a18ecd256fdb48a9c9d2d01448-8754334-images-thumbs&n=13',
 1000, 15),
(9,'розжиг','жидкость для росжига, шт',
 'https://avatars.mds.yandex.net/get-eda/3735503/f12e08c905a8e79cb626dbd1c81d821e/orig',
 500, 2);

insert into users (id,login, nickname, password, age, gender) values
(1,'abobus','abobus','abobus123456',20,'М'),
(2,'bebrik','bebrik','bebrik1323raf1',30,'М'),
(3,'KILLER2007','dawg','amput.ant1',40,'М');


insert into events (id,title,description,link_event_site,link_image,
                    price,created_at,is_active,id_user_created,tags) values
(1,'шашлычки','на природе, при погоде','https://a.d-cd.net/ae8ffcds-1920.jpg',
 'https://pro-dachnikov.com/uploads/posts/2021-10/1634624751_88-p-mangal-shashlik-foto-97.jpg',
 30000,'20120618 10:34:09 AM',true,1,'пикник,шашлыки'),
(2,'отдых на море','на море стилька раздать','https://klike.net/uploads/posts/2023-01/1675146871_3-3.jpg',
 'https://vsegda-pomnim.com/uploads/posts/2022-04/1651012769_51-vsegda-pomnim-com-p-khorvatiya-more-foto-58.jpg',
 45000,'2023-06-21 12:34:09 PM',false, 2,'море'),
(3,'поход', 'поход в лес','https://vsegda-pomnim.com/uploads/posts/2022-04/1648957970_21-vsegda-pomnim-com-p-khvoinie-lesa-foto-32.jpg',
 'https://phonoteka.org/uploads/posts/2023-03/1679556522_phonoteka-org-p-opushka-lesa-foni-instagram-17.jpg',
 30000,'2023-06-25 12:34:09 PM',false, 3,'активный отдых в лесу');