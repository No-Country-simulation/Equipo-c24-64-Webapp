export const calculatePrice = (checkIn, checkOut, price) => {
  if (!checkIn || !checkOut || !price)
    return { nightQuantity: 0, totalPrice: 0 };

  const nightQuantity =
    (new Date(checkOut) - new Date(checkIn)) / (1000 * 60 * 60 * 24);

  const subTotalPrice = nightQuantity * price;
  const iva = 1.21;
  const totalPrice = subTotalPrice * iva;
  const ivaInDollars = totalPrice - subTotalPrice;
  return { nightQuantity, totalPrice, subTotalPrice, iva, ivaInDollars };
};
