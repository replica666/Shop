-- data.sql

-- Insert data for the Product table
INSERT INTO products (
    code,
    name,
    description,
    price,
    quantity,
    inventory_status,
    category,
    image,
    rating,
    internal_reference,
    shell_id
) VALUES
      (
          'f230fh0g3',
          'Bamboo Watch',
          'Product Description',
          65,
          24,
          'INSTOCK',
          'Accessories',
          'bamboo-watch.jpg',
          5,
          'INT_REF_001',
          NULL
      ),
      (
          'nvklal433',
          'Black Watch',
          'Product Description',
          72,
          61,
          'INSTOCK',
          'Accessories',
          'black-watch.jpg',
          4,
          'INT_REF_002',
          NULL
      ),
      (
          'zz21cz3c1',
          'Blue Band',
          'Product Description',
          79,
          2,
          'LOWSTOCK',
          'Fitness',
          'blue-band.jpg',
          3,
          'INT_REF_003',
          NULL
      ),
      (
          '244wgerg2',
          'Blue T-Shirt',
          'Product Description',
          29,
          25,
          'INSTOCK',
          'Clothing',
          'blue-t-shirt.jpg',
          5,
          'INT_REF_004',
          NULL
      ),
      (
          'h456wer53',
          'Bracelet',
          'Product Description',
          15,
          73,
          'INSTOCK',
          'Accessories',
          'bracelet.jpg',
          4,
          'INT_REF_005',
          NULL
      ),
      (
          'gwuby345v',
          'Yoga Set',
          'Product Description',
          20,
          25,
          'INSTOCK',
          'Fitness',
          'yoga-set.jpg',
          8,
          'INT_REF_006',
          NULL
      );
