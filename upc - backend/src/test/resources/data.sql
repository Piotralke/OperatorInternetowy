--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-06-15 21:04:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 41416)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id bigint NOT NULL,
    address character varying(255),
    balance double precision,
    email character varying(255),
    first_name character varying(255),
    is_business_client boolean,
    last_name character varying(255),
    nip character varying(255),
    password character varying(255),
    pesel character varying(255),
    phone_number character varying(255),
    roles character varying(255),
    uuid uuid
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 41415)
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO postgres;

--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 214
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- TOC entry 217 (class 1259 OID 41425)
-- Name: contracts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contracts (
    contract_id bigint NOT NULL,
    amount double precision,
    end_date timestamp(6) without time zone,
    start_date timestamp(6) without time zone,
    uuid uuid,
    offer_entity_id bigint
);


ALTER TABLE public.contracts OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 41424)
-- Name: contracts_contract_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contracts_contract_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contracts_contract_id_seq OWNER TO postgres;

--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 216
-- Name: contracts_contract_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contracts_contract_id_seq OWNED BY public.contracts.contract_id;


--
-- TOC entry 219 (class 1259 OID 41432)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id bigint NOT NULL,
    address character varying(255),
    contract_form smallint,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    nip character varying(255),
    password character varying(255),
    pesel character varying(255),
    phone_number character varying(255),
    roles character varying(255),
    salary double precision,
    uuid uuid,
    workplace character varying(255)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 41431)
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employees_id_seq OWNER TO postgres;

--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 218
-- Name: employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;


--
-- TOC entry 221 (class 1259 OID 41441)
-- Name: notices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notices (
    notice_id bigint NOT NULL,
    description character varying(255),
    is_clicked boolean,
    notice_date timestamp(6) without time zone,
    uuid uuid,
    id bigint
);


ALTER TABLE public.notices OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 41440)
-- Name: notices_notice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notices_notice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notices_notice_id_seq OWNER TO postgres;

--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 220
-- Name: notices_notice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notices_notice_id_seq OWNED BY public.notices.notice_id;


--
-- TOC entry 223 (class 1259 OID 41448)
-- Name: offers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offers (
    id bigint NOT NULL,
    description text NOT NULL,
    is_archival boolean,
    name character varying(255),
    offer_type smallint,
    price double precision,
    uuid uuid,
    with_device boolean,
    product_entity_product_id bigint
);


ALTER TABLE public.offers OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 41447)
-- Name: offers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.offers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offers_id_seq OWNER TO postgres;

--
-- TOC entry 3445 (class 0 OID 0)
-- Dependencies: 222
-- Name: offers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.offers_id_seq OWNED BY public.offers.id;


--
-- TOC entry 225 (class 1259 OID 41457)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    amount double precision,
    order_date timestamp(6) without time zone,
    order_status smallint,
    payment_status smallint,
    uuid uuid,
    id bigint,
    employee_entity_id bigint,
    service_service_id bigint
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 41456)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 3446 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 227 (class 1259 OID 41464)
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
    payment_id bigint NOT NULL,
    amount double precision,
    data timestamp(6) without time zone,
    name character varying(255),
    payment_status smallint,
    products_uuid character varying(255),
    uuid uuid,
    contract_id bigint
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 41463)
-- Name: payments_payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payments_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payments_payment_id_seq OWNER TO postgres;

--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 226
-- Name: payments_payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payments_payment_id_seq OWNED BY public.payments.payment_id;


--
-- TOC entry 229 (class 1259 OID 41473)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id bigint NOT NULL,
    description text NOT NULL,
    name character varying(255),
    price double precision,
    product_type character varying(255),
    uuid uuid,
    order_id bigint
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 41472)
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_product_id_seq OWNER TO postgres;

--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 228
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_product_id_seq OWNED BY public.products.product_id;


--
-- TOC entry 231 (class 1259 OID 41482)
-- Name: services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.services (
    service_id bigint NOT NULL,
    name character varying(255),
    offer_type smallint,
    uuid uuid,
    id bigint,
    contract_id bigint
);


ALTER TABLE public.services OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 41481)
-- Name: services_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.services_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.services_service_id_seq OWNER TO postgres;

--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 230
-- Name: services_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.services_service_id_seq OWNED BY public.services.service_id;


--
-- TOC entry 233 (class 1259 OID 41489)
-- Name: user_problems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_problems (
    user_problem_id bigint NOT NULL,
    description text,
    user_problem_end_date timestamp(6) without time zone,
    user_problem_start_date timestamp(6) without time zone,
    user_problem_status smallint,
    uuid uuid,
    id bigint
);


ALTER TABLE public.user_problems OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 41488)
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_problems_user_problem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_problems_user_problem_id_seq OWNER TO postgres;

--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 232
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_problems_user_problem_id_seq OWNED BY public.user_problems.user_problem_id;


--
-- TOC entry 235 (class 1259 OID 41498)
-- Name: warehouses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouses (
    id bigint NOT NULL,
    product_name character varying(255),
    quantity integer,
    uuid uuid,
    product_entity_product_id bigint
);


ALTER TABLE public.warehouses OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 41497)
-- Name: warehouses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.warehouses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.warehouses_id_seq OWNER TO postgres;

--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 234
-- Name: warehouses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.warehouses_id_seq OWNED BY public.warehouses.id;


--
-- TOC entry 3223 (class 2604 OID 41419)
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- TOC entry 3224 (class 2604 OID 41428)
-- Name: contracts contract_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts ALTER COLUMN contract_id SET DEFAULT nextval('public.contracts_contract_id_seq'::regclass);


--
-- TOC entry 3225 (class 2604 OID 41435)
-- Name: employees id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);


--
-- TOC entry 3226 (class 2604 OID 41444)
-- Name: notices notice_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices ALTER COLUMN notice_id SET DEFAULT nextval('public.notices_notice_id_seq'::regclass);


--
-- TOC entry 3227 (class 2604 OID 41451)
-- Name: offers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers ALTER COLUMN id SET DEFAULT nextval('public.offers_id_seq'::regclass);


--
-- TOC entry 3228 (class 2604 OID 41460)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 3229 (class 2604 OID 41467)
-- Name: payments payment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments ALTER COLUMN payment_id SET DEFAULT nextval('public.payments_payment_id_seq'::regclass);


--
-- TOC entry 3230 (class 2604 OID 41476)
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN product_id SET DEFAULT nextval('public.products_product_id_seq'::regclass);


--
-- TOC entry 3231 (class 2604 OID 41485)
-- Name: services service_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services ALTER COLUMN service_id SET DEFAULT nextval('public.services_service_id_seq'::regclass);


--
-- TOC entry 3232 (class 2604 OID 41492)
-- Name: user_problems user_problem_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems ALTER COLUMN user_problem_id SET DEFAULT nextval('public.user_problems_user_problem_id_seq'::regclass);


--
-- TOC entry 3233 (class 2604 OID 41501)
-- Name: warehouses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses ALTER COLUMN id SET DEFAULT nextval('public.warehouses_id_seq'::regclass);


--
-- TOC entry 3415 (class 0 OID 41416)
-- Dependencies: 215
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (1, '64640 Upton Spurs, Port Cliffview, GA 27920', 0, 'tynisha.bergstrom@gmail.com', 'Darrell', false, 'Schmidt', NULL, 'client0', NULL, '1-310-276-7229', 'USER', '522346b2-acf8-449d-99f5-c10f491f3658');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (2, 'Suite 953 077 Keena Mall, Crooksville, VT 14098', 0, 'roberto.langworth@yahoo.com', 'Ashlee', false, 'Rau', NULL, 'client1', NULL, '(510) 842-1549 x27204', 'USER', 'a34040b4-68f0-4425-8d4c-8f2f56661f52');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (3, 'Suite 471 2853 Rohan Wall, South Ryanshire, GA 65490-1881', 0, 'clora.keeling@hotmail.com', 'Lon', false, 'Raynor', NULL, 'client2', NULL, '1-749-731-6679 x9192', 'USER', '48ee73f1-0ff9-465b-b463-419c796aa77b');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (4, '0607 Ingrid Via, Port Lonnie, TX 73632-8138', 0, 'leon.steuber@yahoo.com', 'Mauro', false, 'Gusikowski', NULL, 'client3', NULL, '1-172-142-1893 x7397', 'USER', '9d7ba357-5b11-4526-a44b-ab314b528233');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (5, 'Suite 107 9316 Kunde Ranch, Stromanburgh, ND 96367-6919', 0, 'wan.johnston@gmail.com', 'Gonzalo', false, 'Stiedemann', NULL, 'client4', NULL, '(681) 616-2369 x799', 'USER', '03982fc6-5d8e-471d-b418-42a4e2600c62');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (6, 'Apt. 117 4308 Murazik Walks, Funkport, IN 31098-0283', 0, 'newton.lehner@hotmail.com', 'Elbert', false, 'Hoeger', NULL, 'client5', NULL, '1-838-333-4992', 'USER', 'b84645b8-c9f4-41a5-8ff9-a961e194500e');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (7, '042 Kassulke Pine, East Kylefurt, VA 48324', 0, 'andree.larson@hotmail.com', 'Robert', false, 'Schamberger', NULL, 'client6', NULL, '757-694-9504', 'USER', '28ecd9cf-57da-4cf4-8574-325793906e63');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (8, '85083 Jeffery Harbors, North Wilburn, MD 69902', 0, 'lorretta.corwin@hotmail.com', 'Sheryll', false, 'Hackett', NULL, 'client7', NULL, '924-101-6842 x0101', 'USER', '17e95882-9b97-4b68-8b72-056178d5f3e1');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (9, 'Apt. 750 1820 Romaine Loop, North Shannon, CT 71863', 0, 'maryanna.dare@gmail.com', 'Maurita', false, 'Kub', NULL, 'client8', NULL, '746-169-9751', 'USER', '62f2a1b1-ebbb-4f53-b8c8-92883d9b49f5');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (10, 'Apt. 269 325 Hettinger Heights, Lake Breanaborough, DE 82862', 0, 'britt.abshire@gmail.com', 'Delena', false, 'Wiegand', NULL, 'client9', NULL, '(307) 790-4273 x84456', 'USER', '10545c64-8888-4422-bde5-92979ec772b2');


--
-- TOC entry 3417 (class 0 OID 41425)
-- Dependencies: 217
-- Data for Name: contracts; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3419 (class 0 OID 41432)
-- Dependencies: 219
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employees (id, address, contract_form, email, first_name, last_name, nip, password, pesel, phone_number, roles, salary, uuid, workplace) VALUES (1, NULL, 0, 'jarod.howell@yahoo.com', 'Alejandra', 'Langworth', NULL, 'admin', NULL, '661412255', 'ADMIN', 12000, '53bd49ca-9599-4c9a-b179-9c6d597845b5', 'Kielce');
INSERT INTO public.employees (id, address, contract_form, email, first_name, last_name, nip, password, pesel, phone_number, roles, salary, uuid, workplace) VALUES (2, NULL, 0, 'dawn.macgyver@yahoo.com', 'Ona', 'Predovic', NULL, 'worker', NULL, '661412254', 'WORKER', 7000, 'c196e6de-850c-4c42-9770-95bca42339ab', 'Kielce');


--
-- TOC entry 3421 (class 0 OID 41441)
-- Dependencies: 221
-- Data for Name: notices; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3423 (class 0 OID 41448)
-- Dependencies: 223
-- Data for Name: offers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (1, 'Internet światłowodowy', false, 'Internet 300Mb/s', 0, 50, '4461593f-b666-47eb-912d-65f677194724', true, 1);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (2, 'Internet światłowodowy', false, 'Internet 600Mb/s', 0, 60, '63b26eec-b59e-473e-823a-c774d100b38c', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (3, 'Internet światłowodowy', false, 'Internet 1Gb/s', 0, 80, 'efadd9fc-e334-4f7a-96c8-6b1b2f650779', true, 1);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (4, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 50 kanałów', 1, 20, '01dfa47d-2a60-4c21-ad17-03892a3aa4d0', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (5, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 136 kanałów', 1, 40, '20a16b9e-dc81-4e62-8dc5-4e34dd5d4147', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (6, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 179 kanałów', 1, 50, '0d045cd5-393b-43ae-811d-2cccd1852997', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (7, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 300Mb/s + Telewizja 50 kanałów', 2, 85, '3146e468-0544-441e-bba3-ad0bf43738fc', true, 3);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (8, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 600Mb/s + Telewizja 136 kanałów', 2, 110, '7bcadaab-b153-4128-a28f-cb75cdf6d9cf', true, 1);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (9, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 1Gb/s + Telewizja 179 kanałów', 2, 140, 'f5924b75-347b-44de-9705-702109da3ff2', true, 2);


--
-- TOC entry 3425 (class 0 OID 41457)
-- Dependencies: 225
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3427 (class 0 OID 41464)
-- Dependencies: 227
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3429 (class 0 OID 41473)
-- Dependencies: 229
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (1, 'Ciesz się mocnym i stabilnym sygnałem Wi-Fi jak nigdy wcześniej. Deco M4 wykorzystuje technologię TP-Link Mesh, aby zapewnić silny sygnał bezprzewodowy w każdym kącie twojego domu. Jednostki Deco współpracują ze sobą, aby tworzyć jednolitą sieć Wi-Fi.

Dwie jednostki Deco pokrywają siecią powierzchnię do 260m2. Połączenia bezprzewodowe i opcjonalne łącze rezerwowe Ethernet łączą jednostki Deco, zapewniając jeszcze szybszą sieć i najbardziej płynny roaming. Potrzebujesz większego zasięgu Wi-Fi? Wystarczy dodać kolejną jednostkę Deco.', 'TP-LINK Deco M4 Mesh WiFi System', 408, 'INTERNET', 'e3154cab-640e-49cf-9b7e-f537578a72be', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (2, 'Dwupasmowa sieć w standardzie AX3000 to rozwiązanie technologiczne zastosowane w routerze TP-LINK Archer AX50. Model ten umożliwia zatem wykorzystanie obu zakresów częstotliwości połączenia z Internetem – zarówno 2,4, jak i 5 GHz. Takie połączenie gwarantuje wysoką przepustowość sieci domowej, co z kolei przekłada się na wygodę jej użytkowania. Dzięki routerowi TP-LINK Archer AX50 możesz bez przeszkód i limitów oglądać ulubione filmy, grać w gry online czy transmitować swoją rozgrywkę lub inne klipy wideo metodą przesyłania strumieniowego.', 'Router TP-LINK Archer AX50', 349.99, 'INTERNET', 'c2a9abc1-19df-4057-aaea-60db9e2c6a6a', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (3, 'Sięgnij po standard Wi-Fi nowej generacji zapewniający przepustowość nawet 3000 Mb/s. ASUS RT-AX58U to dwuzakresowy router Wi-Fi 2x2 obsługujący najnowszy standard 802.11ax, zapewniając tym samym większą przepustowość i stabilność połączenia, przy podłączeniu wielu urządzeń sieciowych jednocześnie. Przy tym znacząco poprawia bezpieczeństwo Twoje i Twoich bliskich podczas korzystania z Internetu, wykorzystując w tym celu najnowsze rozwiązania na rynku.', 'Router ASUS RT-AX58U V2 Wi-Fi 6 AX3000', 538.9, 'INTERNET', '12451203-e7ee-4b91-9368-aeba3bfed432', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (4, 'Dwupasmowy, gigabitowy router Wi‑Fi 6 AX3000 TP-Link Archer AX53 oferuje prędkości nowej generacji dochodzące do 2402 Mb/s w paśmie 5 GHz i do 574 Mb/s w paśmie 2,4 GHz, zapewniając płynną transmisję i wyższe prędkości pobierania. Napędzany przez potężny, dwurdzeniowy procesor, ten w pełni wyposażony Router Wi-Fi 6 umożliwia wykonywanie wielu zadań działających płynnie w tym samym czasie. Korzystaj ze streamingu i gier jak nigdy przedtem.', 'Router TP-LINK Archer AX53', 349, 'INTERNET', '23e0061b-99d4-4540-8432-43f7055fce5f', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (5, 'Żywe kolory, kontrast, wyraziste szczegóły i głębia to cechy, które tworzą obraz doskonały. Wszystko to dostarczy Ci telewizor TCL 58P635. Rozdzielczość 4K sprawia, że ekran wyświetla ponad 8 milionów pikseli, dzięki którym zobaczysz różnicę. Model ten sprawdzi się zarówno do oglądania filmów, czy seriali, ale także do grania. Został on wyposażony w złącze HDMI 2.1 z ALLM, dzięki któremu responsywność oraz jakość obrazu będą na bardzo wysokim poziomie. To bardzo ważny parametr dla każdego gracza. Co więcej, telewizor ma bezramkową konstrukcję, dzięki której obraz wciągnie Cię jeszcze bardziej. Natomiast Smart TV z platformą Google zapewni dostęp do różnych treści dla całej rodziny.', 'TCL 58P635', 1999, 'TV', '2c36384a-84b4-434d-9609-b98e6b524688', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (6, '55-calowy telewizor Sony KD-55X85K został wyposażony w procesor 4K HDR Processor X1™ oraz technologie TRILUMINOS PRO™ i 4K X-Reality™ PRO, a także panel z odświeżaniem 120 Hz. Takie rozwiązanie pozwala, by urządzenie zachwyciło Cię urzekającymi kolorami dbającymi o niezwykły realizm. Ponadto telewizor zapewni Ci doskonałą rozdzielczość 4K niezależnie od źródła sygnału. Co więcej, o czysty dźwięk zadbają głośniki X-Balanced Speaker.', 'Sony KD-55X85K', 3999, 'TV', '81ac8656-f3a2-47e2-9db5-01b9e3e99b3e', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (7, 'Xiaomi Mi LED TV A2 to 55-calowy telewizor z wyświetlaczem Premium 4K Ultra HD. Szeroka gama kolorów, wsparcie HDR10, Dolby Vision i HLG, czy technologia MEMC sprawią, że obraz będzie wyrazisty i płynny. Urządzenie ma wbudowaną łączność Wi-Fi oraz Bluetooth. Dzięki temu możesz bez przeszkód korzystać z platformy Smart TV Android. Co więcej, telewizor obsługuje dekodowanie Dolby, a także DTS-HD, FLAC, czy AAC. Dzięki temu wysokiej jakości obraz jest uzupełniony o satysfakcjonujące brzmienie. Natomiast dekoder DVB-T2 pozwoli korzystać z nowego standardu telewizji naziemnej.', 'Xiaomi Mi LED TV A2 55"', 2219, 'TV', '645219d4-94e2-4717-a82e-4dd31ca9569d', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (8, 'Pozwól, by 65-calowy ekran OLED evo telewizora LG OLED65C21LA stał się Twoim, oknem na nowy, jasny świat. Urządzenie zostało wyposażone w technologię Brightness Booster, która zapewni Ci o 20% większą jasność, będąc jednocześnie ekranem przyjaznym dla oczu. Ponadto telewizor oferuje Szybki procesor α9 Gen 5 AI o niesamowite prędkości, a także wsparcie sztucznej inteligencji ThinQ AI w języku polskim. Co więcej, LG OLED65C21LA da Ci również dostęp do wielu serwisów streamingowych takich jak: Amazon Prime, Apple TV+, Disney+, HBO MAX, czy Netflix. Dodatkowo dzięki technologiom Dolby Vision IQ oraz Dolby Atmos zastosowanym w telewizorze będziesz mógł cieszyć się prawdziwie kinowymi wrażeniami w Twoim salonie.', 'LG OLED65C21LA', 7990, 'TV', 'e18aca8b-5ef2-4cda-a2e3-8fafbb8f276d', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (9, 'Samsung Galaxy S23 to smartfon kompletny. Szczególnie spodoba się użytkownikom, którzy preferują telefony bez problemu mieszczące się w kieszeni i jednocześnie potrzebują sprzętu z górnej półki. Wysoka wydajność, ogromne możliwości fotograficzne, świetny ekran, dobry czas pracy baterii i ładny design – to cechy, które najbardziej wyróżniają Galaxy S23.', 'Samsung Galaxy S23 8/256GB', 4799, 'MOBILE', '423bc4f8-ae88-4344-a945-670a2d0dd434', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (10, 'Sprawdź, co może zaoferować Ci Motorola edge 40 5G 256 GB Coronet Blue. Elegancka obudowa pewnie leży w dłoni i świetnie komponuje się z każdym stylem. Nie obawiaj się przypadkowego zachlapania wodą. Klasa odporności IP68 dba, aby woda nie dostała się do wnętrza smartfona. Rób piękne i ciekawy zdjęcia, które zapiszą Twoje wspomnienia w wysokiej rozdzielczości.', 'Motorola edge 40 5G 8/256GB', 2999, 'MOBILE', 'c73644b7-f8c2-48c3-b555-6577fc1be9a3', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (11, 'Sprawdź, co to znaczy zabawa bez ograniczeń. Odkrywaj wirtualny świat razem z Samsung Galaxy M33 5G 128 GB Green. Baw się, graj, oglądaj filmy i słuchaj muzyki z wydajnym smartfonem, którego bateria wystarczy Ci na cały dzień wrażeń. Twórz wspomnienia z pomocą telefonu i zapisuj je w najlepszej jakości, aby wracając do nich, poczuć się znów, jak na wakacjach. Czerp wiele radości z wyświetlanym treści na dużym ekranie z wysoką częstotliwością odświeżania, która nie pozwoli na żadne opóźnienia.', 'Samsung Galaxy M33 5G 6/128', 1099, 'MOBILE', '0c101cc3-d396-49a3-a1b2-9b7afa802568', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (12, 'Poznaj nową odsłonę serii smartfonów, która zrodziła się z piękna. Przed Tobą Huawei P60 Pro 256 GB czarny. Zapewne już wiesz, czego możesz spodziewać się po tym modelu. To wyjątkowa dbałość o jakość wykonania oraz najlepsze możliwości fotograficzne.

Nowe aparaty XMAGE i obiektyw w regulowaną przysłoną pozwoli Ci poczuć się jak prawdziwy profesjonalista. Rób niesamowite zdjęcia w każdych warunkach i podziwiaj je na przestronny ekranie OLED o rozmiarze 6,67”. Moc do działania dostarczy Ci nowoczesny procesor, sporo pamięci RAM oraz wytrzymała bateria o sporej pojemności. Wybierz Huawei P60 Pro i poznaj jakość premium.', 'Huawei P60 Pro 8/256GB', 5499, 'MOBILE', '1fe06a6a-bf68-4d01-8b41-c4376358bdc5', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (13, 'Odkrywaj wirtualny świat razem z Xiaomi POCO X5 Pro 5G 256 GB Black i ciesz się jakością, jaką oferuje. Solidnie wykonana konstrukcja sprawdzi się doskonale podczas intensywnych i pełnych wrażeń dni. Poza tym smartfon dysponuje pojemną baterią, więc możesz korzystać z niego cały dzień. Dziel się treściami z przyjaciółmi, graj w nowe mobilne tytuły i prowadź długie rozmowy telefoniczne z bliskimi. Rób świetnej jakości zdjęcia i ciesz się z wyraźnym wspomnień wakacyjnego szaleństwa.', 'Xiaomi POCO X5 Pro 5G 8/256GB', 1799, 'MOBILE', '116d7422-d34a-41cf-9db2-5838fc2b60c7', NULL);


--
-- TOC entry 3431 (class 0 OID 41482)
-- Dependencies: 231
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3433 (class 0 OID 41489)
-- Dependencies: 233
-- Data for Name: user_problems; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3435 (class 0 OID 41498)
-- Dependencies: 235
-- Data for Name: warehouses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (1, 'TP-LINK Deco M4 Mesh WiFi System', 17, 'e3154cab-640e-49cf-9b7e-f537578a72be', 1);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (2, 'Router TP-LINK Archer AX50', 4, 'c2a9abc1-19df-4057-aaea-60db9e2c6a6a', 2);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (3, 'Router ASUS RT-AX58U V2 Wi-Fi 6 AX3000', 9, '12451203-e7ee-4b91-9368-aeba3bfed432', 3);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (4, 'Router TP-LINK Archer AX53', 21, '23e0061b-99d4-4540-8432-43f7055fce5f', 4);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (5, 'TCL 58P635', 8, '2c36384a-84b4-434d-9609-b98e6b524688', 5);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (6, 'Sony KD-55X85K', 11, '81ac8656-f3a2-47e2-9db5-01b9e3e99b3e', 6);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (7, 'Xiaomi Mi LED TV A2 55"', 16, '645219d4-94e2-4717-a82e-4dd31ca9569d', 7);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (8, 'LG OLED65C21LA', 4, 'e18aca8b-5ef2-4cda-a2e3-8fafbb8f276d', 8);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (9, 'Samsung Galaxy S23 8/256GB', 11, '423bc4f8-ae88-4344-a945-670a2d0dd434', 9);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (10, 'Motorola edge 40 5G 8/256GB', 4, 'c73644b7-f8c2-48c3-b555-6577fc1be9a3', 10);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (11, 'Samsung Galaxy M33 5G 6/128', 22, '0c101cc3-d396-49a3-a1b2-9b7afa802568', 11);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (12, 'Huawei P60 Pro 8/256GB', 7, '1fe06a6a-bf68-4d01-8b41-c4376358bdc5', 12);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (13, 'Xiaomi POCO X5 Pro 5G 8/256GB', 3, '116d7422-d34a-41cf-9db2-5838fc2b60c7', 13);


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 214
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_seq', 17, true);


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 216
-- Name: contracts_contract_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contracts_contract_id_seq', 383, true);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 218
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 7, true);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 220
-- Name: notices_notice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notices_notice_id_seq', 64, true);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 222
-- Name: offers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.offers_id_seq', 40, true);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 383, true);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 226
-- Name: payments_payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payments_payment_id_seq', 1, false);


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 228
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_product_id_seq', 40, true);


--
-- TOC entry 3460 (class 0 OID 0)
-- Dependencies: 230
-- Name: services_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_service_id_seq', 383, true);


--
-- TOC entry 3461 (class 0 OID 0)
-- Dependencies: 232
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_problems_user_problem_id_seq', 120, true);


--
-- TOC entry 3462 (class 0 OID 0)
-- Dependencies: 234
-- Name: warehouses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouses_id_seq', 13, true);


--
-- TOC entry 3235 (class 2606 OID 41423)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 3239 (class 2606 OID 41430)
-- Name: contracts contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_pkey PRIMARY KEY (contract_id);


--
-- TOC entry 3241 (class 2606 OID 41439)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- TOC entry 3245 (class 2606 OID 41446)
-- Name: notices notices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT notices_pkey PRIMARY KEY (notice_id);


--
-- TOC entry 3247 (class 2606 OID 41455)
-- Name: offers offers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT offers_pkey PRIMARY KEY (id);


--
-- TOC entry 3249 (class 2606 OID 41462)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3251 (class 2606 OID 41471)
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);


--
-- TOC entry 3253 (class 2606 OID 41480)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 3255 (class 2606 OID 41487)
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (service_id);


--
-- TOC entry 3243 (class 2606 OID 41507)
-- Name: employees uk_j9xgmd0ya5jmus09o0b8pqrpb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT uk_j9xgmd0ya5jmus09o0b8pqrpb UNIQUE (email);


--
-- TOC entry 3237 (class 2606 OID 41505)
-- Name: clients uk_srv16ica2c1csub334bxjjb59; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT uk_srv16ica2c1csub334bxjjb59 UNIQUE (email);


--
-- TOC entry 3257 (class 2606 OID 41496)
-- Name: user_problems user_problems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems
    ADD CONSTRAINT user_problems_pkey PRIMARY KEY (user_problem_id);


--
-- TOC entry 3259 (class 2606 OID 41503)
-- Name: warehouses warehouses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses
    ADD CONSTRAINT warehouses_pkey PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 41508)
-- Name: contracts fk59akclxqdt8wqwewlmfxqqpqe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT fk59akclxqdt8wqwewlmfxqqpqe FOREIGN KEY (offer_entity_id) REFERENCES public.offers(id);


--
-- TOC entry 3271 (class 2606 OID 41563)
-- Name: warehouses fk7guygs8v0g9thpqkxvqiy1hja; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses
    ADD CONSTRAINT fk7guygs8v0g9thpqkxvqiy1hja FOREIGN KEY (product_entity_product_id) REFERENCES public.products(product_id);


--
-- TOC entry 3263 (class 2606 OID 41533)
-- Name: orders fk840b0ihty8egeps54dslxxpm0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk840b0ihty8egeps54dslxxpm0 FOREIGN KEY (service_service_id) REFERENCES public.services(service_id);


--
-- TOC entry 3268 (class 2606 OID 41548)
-- Name: services fkaqn83tv87vhytdu1w8cb5ctqt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT fkaqn83tv87vhytdu1w8cb5ctqt FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3269 (class 2606 OID 41553)
-- Name: services fkioxlygx0qp4x0yf96pc981gv6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT fkioxlygx0qp4x0yf96pc981gv6 FOREIGN KEY (contract_id) REFERENCES public.contracts(contract_id);


--
-- TOC entry 3261 (class 2606 OID 41513)
-- Name: notices fkjfgspyc1k9b8hlfp9mcfy4kfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fkjfgspyc1k9b8hlfp9mcfy4kfe FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3264 (class 2606 OID 41523)
-- Name: orders fkk1ri6s69qqq84rdxoc8556310; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkk1ri6s69qqq84rdxoc8556310 FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3270 (class 2606 OID 41558)
-- Name: user_problems fknc3agf56q54yc1ekyypsccqxm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems
    ADD CONSTRAINT fknc3agf56q54yc1ekyypsccqxm FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3262 (class 2606 OID 41518)
-- Name: offers fkp6ugshsymg2knhiy9sseuk0w8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fkp6ugshsymg2knhiy9sseuk0w8 FOREIGN KEY (product_entity_product_id) REFERENCES public.products(product_id);


--
-- TOC entry 3266 (class 2606 OID 41538)
-- Name: payments fkqywegtqyijw241foqfkseq1l6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT fkqywegtqyijw241foqfkseq1l6 FOREIGN KEY (contract_id) REFERENCES public.contracts(contract_id);


--
-- TOC entry 3267 (class 2606 OID 41543)
-- Name: products fkr3aftk48ylvntpui7l04kbcc1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkr3aftk48ylvntpui7l04kbcc1 FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 3265 (class 2606 OID 41528)
-- Name: orders fkrk0sc89d5h7e9rnjkjxwj95gf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkrk0sc89d5h7e9rnjkjxwj95gf FOREIGN KEY (employee_entity_id) REFERENCES public.employees(id);


-- Completed on 2023-06-15 21:04:40

--
-- PostgreSQL database dump complete
--

