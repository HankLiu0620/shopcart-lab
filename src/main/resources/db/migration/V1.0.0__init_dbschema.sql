-- Table: public.goods

CREATE TABLE public.goods
(
    id character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    unit_price integer NOT NULL,
    count integer NOT NULL,
    cart_id integer NOT NULL,
    CONSTRAINT goods_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.goods
    OWNER to postgres;

COMMENT ON COLUMN public.goods.id
    IS '商品編號';

COMMENT ON COLUMN public.goods.name
    IS '商品名稱';

COMMENT ON COLUMN public.goods.unit_price
    IS '單價';

COMMENT ON COLUMN public.goods.count
    IS '數量';

COMMENT ON COLUMN public.goods.cart_id
    IS '購物車編號';
	
--------------------------------------------------------------------------------

-- Table: public.shop_cart

CREATE TABLE public.shop_cart
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    total_amount integer NOT NULL DEFAULT 0,
    customer_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cart_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.shop_cart
    OWNER to postgres;

COMMENT ON COLUMN public.shop_cart.id
    IS '購物車編號';

COMMENT ON COLUMN public.shop_cart.total_amount
    IS '總金額';

COMMENT ON COLUMN public.shop_cart.customer_name
    IS '消費者名稱';