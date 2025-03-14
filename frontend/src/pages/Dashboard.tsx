import Form from "@/components/dashboard/Form.js";
import Header from "@/layouts/Header";

function Dashboard() {
  return (
    <>
      <Header />
      <div className="mt-16">
        <Form />
      </div>
      {/* <Rooms /> */}
    </>
  );
}

export default Dashboard;
